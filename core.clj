;;Matt Yale      ;; will need this later (spit "results.txt" :append true)
;;Fall 2015
(use 'clojure.java.io)
(require '[clojure.java [io :as io]])
(use 'clojure.string)
;;I assume we aren't allowed to use this ? https://github.com/Engelberg/instaparse
(declare isATerm)
(declare isAFactor)
(declare isWrappedExpression)
(declare grammarCHECK)
(def thecount 0)

(defn grammarCHECK [aline]
    (while (= (nth (split aline #"\s+") (thecount) "nothing found") "nothing found")
    (do
         (print thecount) (print "debugging function")
         (isAFactor (nth (split aline #"\s+") (thecount) "nothing found"))
         (print "in the loop")


  (def i++ (inc thecount))
  (i++)

  )
    ))



(with-open [r (io/reader "input.txt")]
   (doseq [line (line-seq r)]
      (spit "results.txt"  (grammarCHECK line))
      (spit "output.txt" (str (join "\n" (split line #"\s+")) "\n") :append true)

   )
)

(spit "tokken.txt" (reduce conj #{} (line-seq (io/reader "output.txt"))) :append true)    ;;ended up not using tokkens to make a parse table
;;We now have a set of each lexeme from the input, no duplicates. We need to match these to tokkens
;;17 possible tokkens in given input file






;;helper functions
(defn isCloseParen [x] ;;To do

  )
(defn isOpenParen [theexpression]   ;; TO FINISH
  (if (re-matches "(") theexpression)
  )
(defn isATailTerm [theterm]   ;TO DO

  )
(defn isATailFactor [thefactor]

  )
;;Given Gramar;;
;; EXPRESSION ->  TERM  {  ( + | - ) TERM }
(defn isAExpression [theexpression]
  (if(or (isATerm theexpression) (isATailTerm theexpression)) true false)
  )
;; TERM -> FACTOR { ( * | / ) FACTOR }
(defn isATerm [theterm]
  (if (or (isAFactor theterm) (isATailFactor theterm)) true false)
  )
;; FACTOR -> ID  |  INT  | (EXPRESSION)  ;COMPLETE
(defn isAFactor [thefactor]
  (if (or (re-matches #"\w+" thefactor) (re-matches #"\d+" thefactor)) true  (if (isOpenParen thefactor) true false))
  )
;; Reference : http://stackoverflow.com/questions/19412624/pass-multiple-parameters-function-from-other-function-with-clojure-and-readabili
;; Reference : http://clojuredocs.org/clojure.core/apply
;; Reference :  http://clojure.org/cheatsheet
;; good god... this syntax is hard.
