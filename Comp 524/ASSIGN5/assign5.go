package main

import (
	"time"
	"fmt"
	"log"
	"net"
	"bufio"
)

type client chan<- string // an outgoing message channel

var (
	entering = make(chan client)
	leaving  = make(chan client)
	messages = make(chan string) // all incoming client messages
	timeout = make(chan client)
)

func broadcaster() {
	clients := make(map[client]bool) // all connected clients
	for {
		select {
		case msg := <-messages:
			for cli := range clients {
				cli <- msg
			}
		case cli := <-entering:
			clients[cli] = true
		case cli := <-leaving:
			delete(clients, cli)
			close(cli)
		}
	}
}

func handleConn(conn net.Conn) {
	ch := make(chan string) // outgoing client messages
	go clientWriter(conn, ch)

	who := conn.RemoteAddr().String()
	ch <- "You are " + who
	messages <- who + " has joined"
	entering <- ch

	input := bufio.NewScanner(conn)
	var i = 1
	for i == 1{
		timer := time.NewTimer(60 * time.Second)
		go func(){
			<-timer.C
			i = 0
			leaving <- ch
			messages <- "Kicked idle user " + who
			conn.Close()
		}()
		if i == 1 && input.Scan() != false{
			messages <- who + ": " + input.Text()
			timer.Stop()
		} else if i== 1 && input.Scan() == false{ 
			timer.Stop()
			leaving <- ch
			messages <- who + " has left"
			conn.Close()
			i = 0
		}
	}
}

func clientWriter(conn net.Conn, ch <-chan string) {
	for msg := range ch {
		fmt.Fprintln(conn, msg) // NOTE: ignoring network errors
	}
}

func main() {
	listener, err := net.Listen("tcp", "localhost:8000")
	if err != nil {
		log.Fatal(err)
	}

	go broadcaster()
	for {
		conn, err := listener.Accept()
		if err != nil {
			log.Print(err)
			continue
		}
		go handleConn(conn)
	}
}
