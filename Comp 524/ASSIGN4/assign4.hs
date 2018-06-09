import Data.List
import Data.Char
import System.Environment

assign4 :: String -> String -> IO [String]
assign4 filePath input = do
    file <- readFile filePath
    let given = map toUpper input
    let words = map (map toUpper) (lines file)
    return [word | word <- words, sort given == sort word]

main = do
    args <- getArgs
    list <- assign4 (head args) (last args)
    print(list)
