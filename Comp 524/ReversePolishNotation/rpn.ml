(*
#load "str.cma"

build with:
ocamlc str.cma rpn.ml 
run with:
./a.out
*)


module RPNCalculator : sig
  val evaluate : string -> float
end = struct
  let convert_operator = function
    | "+" -> (+.)
    | "-" -> (-.)
    | "*" -> ( *. )
    | "/" -> (/.)
    | ope -> failwith ("unsupported operator '" ^ ope ^ "' is used")

  let evaluate rpn =
    let rec calc stack = function
    | [] -> List.hd stack
    | x :: xs when Str.string_match (Str.regexp "^[0-9]+$") x 0 ->
      calc (float_of_string x :: stack) xs
    | x :: xs -> match stack with
      | y1 :: y2 :: ys -> calc (convert_operator x y2 y1 :: ys) xs
      | _ -> failwith "bad pattern" in
    let expr = Str.split (Str.regexp " +") rpn in
    calc [] expr
end

let () =
  let rpn = "1 2 + 3 / 4 - 5 *" in
  print_endline (rpn ^ "\n = " ^ string_of_float (RPNCalculator.evaluate rpn))
