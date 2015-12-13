;;Matt Yale
;;Fall 2015
(use 'clojure.java.io)
(require '[clojure.java [io :as io]])
(use 'clojure.string)
;;I assume we aren't allowed to use this ? https://github.com/Engelberg/instaparse
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
;;17 possible tokkens in given input file

;;Now that we have def tokkens and tokken.txt we will combine them to get a parse table?? But first we need to define the grammar checking functions

;; Reference : http://stackoverflow.com/questions/19412624/pass-multiple-parameters-function-from-other-function-with-clojure-and-readabili
;; Reference : http://clojuredocs.org/clojure.core/apply
;; Reference :  http://clojure.org/cheatsheet
;; good god... this syntax is hard.

;;helper functions
(defn findcloseparen [x] ;;To do
  ;; until end of line look for )
  )
(defn isAWrappedExpression [theexpression]   ;; TO FINISH
  (if (re-matches "("))
  (findcloseparen theexpression)  ;; No close paren? then not a FACTOR
    ;;way to call on inner paren area only
  )

(defn isATailTerm [theterm]   ;TO DO

  )                                                                                          ;;ADD REJECTS LAST SINCE EXTRA CREDI LOGIC WILL NEED TO BE CONSIDERED
;;Given Gramar;;
;; EXPRESSION ->  TERM  {  ( + | - ) TERM }
(defn isAExpression [theexpression]
   if(or (isATerm theexpression) (isATailTerm theexpression))
      "ACCEPT"
  )
;; TERM -> FACTOR { ( * | / ) FACTOR }
(defn isATerm [theterm]
  if (or (isAFactor theterm) (isATailFactor theterm))
  "ACCEPT"
  )
;; FACTOR -> ID  |  INT  | (EXPRESSION)
(defn isAFactor [thefactor]
  (if (or (re-matches #"\w+" thefactor) (re-matches #"\d+" thefactor))
    "ACCEPT")
  (if (isWrappedExpression thetokken))
    "ACCEPT"

  )
;; Addtional details ---- 0 length line end point

;;                   ---- All ID will be 1 char in length, they are case sensitive

;;                   ---- All INT will be >= 0

;;                   ---- no unary minus

;; Output results of each line to screen "ACCEPT or REJECT"

;; Extra credit, why a line is rejected, reason due to grammar








