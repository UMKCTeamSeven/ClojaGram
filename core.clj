;;Matt Yale      ;; will need this later (spit "results.txt" :append true)
;;Fall 2015
(use 'clojure.java.io)
(require '[clojure.java [io :as io]])
(use 'clojure.string)
;;I assume we aren't allowed to use this ? https://github.com/Engelberg/instaparse
(declare isATerm)
(declare isAFactor)
(declare isWrappedExpression)
(declare isOpenParen)
(declare grammarCHECK)
(declare isAExpression)
(declare isATailTerm)
(declare isATailFactor)
;;helper functions
(defn isCloseParen [theexpression]
  (if (re-matches #"\)" theexpression)
    true false)
  )
(defn isOpenParen [theexpression]
  (if (re-matches #"\(" theexpression)
     true false)
  )
(defn isATailTerm [theterm]

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
(defn grammarCHECK [aline]
  (let [tokkentype ""])
  (defn linetravel [aline thecount]
    (nth (split aline #"\s+") thecount "nothing found")
    )
  (loop [thecount 0]
    (when (not= (linetravel aline thecount) "nothing found")
    (do
      (print "debugging function")
      (if (isAFactor (linetravel aline thecount)) (if (isOpenParen (linetravel aline thecount)) (let [tokkentype "("]) (let [tokkentype "FACTOR"]) ) false)
      (if (isATerm (linetravel aline thecount)) true false)
      (if (isAExpression (linetravel aline thecount)) true false)

  )(recur (inc thecount)))
    ))
(with-open [r (reader "input.txt")]
   (doseq [line (line-seq r)]

      (spit "results.txt"  (grammarCHECK line))
      (spit "output.txt" (str (join "\n" (split line #"\s+")) "\n") :append true)
   )
)
(spit "tokken.txt" (reduce conj #{} (line-seq (io/reader "output.txt"))) :append true)    ;;ended up not using tokkens to make a parse table
;;We now have a set of each lexeme from the input, no duplicates. We need to match these to tokkens
;;17 possible tokkens in given input file