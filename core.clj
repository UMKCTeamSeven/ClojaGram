;;Matt Yale
;;Fall 2015

;;Given Gramar;;

;; EXPRESSION ->  TERM  {  ( + | - ) TERM }
;; TERM -> FACTOR { ( * | / ) FACTOR }
;; FACTOR -> ID  |  INT  | (EXPRESSION)

;; Addtional details ---- 0 length line end point

;;                   ---- All ID will be 1 char in length, they are case sensitive

;;                   ---- All INT will be >= 0

;;                   ---- no unary minus

;; Output results of each line to screen "ACCEPT or REJECT"

;; Extra credit, why a line is rejected, reason due to grammar

(use 'clojure.java.io)
(require '[clojure.java [io :as io]])
(use 'clojure.string)

(def tokkens
      #{
         {  :name :id      :pattern #"\w+"  :priority -1 }  ;; Catches case sensitive IDs
         {  :name :minus   :pattern "-" }
         {  :name :assign  :pattern "=" }
         {  :name :divide  :pattern "/" }
         {  :name :plus    :pattern "+" }
         {  :name :mult    :pattern "*" }
         {  :name :lparen  :pattern "(" }
         {  :name :rparen  :pattern ")" }
         {  :name :int     :pattern #"\d+" }
         {  :ignore true   :pattern #"\s+" }})

(with-open [r (reader "input.txt")]
   (doseq [line (line-seq r)]
      (spit "output.txt" (str (join "\n" (split line #"\s+")) "\n") :append true)
   )
)

(println (slurp "output.txt"))
;;output.txt contains each token.
;; lines without the *** are the last tooken in a line.
(spit "tokken.txt" (reduce conj #{} (line-seq (io/reader "output.txt"))) :append true)
;;We now have a set of each lexeme from the input, no duplicates. We need to match these to tokkens
;; (c) seems to be a special case that will need to be broken down further.

    ;; asked about (c) verified it was supposed to be ( c )

;;17 possible tokkens in given input file

;;Now that we have def tokkens and tokken.txt we will combine them to get a parse table

;; Reference : http://stackoverflow.com/questions/19412624/pass-multiple-parameters-function-from-other-function-with-clojure-and-readabili
;; Reference : http://clojuredocs.org/clojure.core/apply

;; good god... this is hard.

(def isAFactor )

(def isATerm )

(def isAExpression )



