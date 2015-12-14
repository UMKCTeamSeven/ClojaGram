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
  (if (or (re-matches #"\+" theterm) (re-matches #"\-" theterm)) true false
    )
  )
(defn isATailFactor [thefactor]
  (if (or (re-matches #"\*" thefactor) (re-matches #"\/" thefactor)) true false
    )
  )
;;Given Gramar;;
;; EXPRESSION ->  TERM  {  ( + | - ) TERM }
(defn isAExpression [theexpression]
  (if (isATerm theexpression)  true false)
  )
;; TERM -> FACTOR { ( * | / ) FACTOR }
(defn isATerm [theterm]
  (if (isAFactor theterm)  true false)
  )
;; FACTOR -> ID  |  INT  | (EXPRESSION)  ;COMPLETE
(defn isAFactor [thefactor]
  (if (or (re-matches #"\w+" thefactor) (re-matches #"\d+" thefactor)) true  (if (isOpenParen thefactor) true false))
  )
(defn grammarCHECK [aline]

  (defn linetravel [aline thecount]
    (nth (split aline #"\s+") thecount "nothing found")
    )
  (loop [thecount 0 tokkentype ""]
    (when (not= (linetravel aline thecount) "nothing found")
      (do

        (if (= tokkentype "") ( (if (isAFactor (linetravel aline thecount)) (if (isOpenParen (linetravel aline thecount)) (let [tokkentype "("]) (let [tokkentype "FACTOR"])) false)
                                (if (isATerm (linetravel aline thecount)) (let [tokkentype "TERM"])) (if (isATailFactor (linetravel aline thecount)) (let [tokkentype (linetravel aline thecount)]) false)
                                (if (isAExpression (linetravel aline thecount)) (let [tokkentype "EXPRESSION"])) (if (isATailTerm (linetravel aline thecount)) (let [tokkentype (linetravel aline thecount)]) false))
          ((if (= tokkentype "EXPRESSION") (spit "result.txt" "ACCEPT" :append true) false)
        (if (= tokkentype "FACTOR")    (if (isAExpression(linetravel aline thecount)) (let [tokkentype (linetravel aline thecount)]) (if (isATailFactor (linetravel aline thecount)) (let [tokkentype (linetravel aline thecount)])
                                                                                                                                     (print "REJECT, Factor not followed by tailfactor" )) ))
        (if (= tokkentype "TERM")      (if (isATailTerm (linetravel aline thecount)) (let [tokkentype (linetravel aline thecount)])
                                                                                                                                       (print "REJECT, term not followed by tailterm" )) )
        (if (or (re-matches #"\+" tokkentype) (re-matches #"\-" tokkentype)) (if (isATerm (linetravel aline thecount)) (spit "result.txt" "ACCEPT" :append true) (print "REJECT, + | - not followed by TERM")))
          (if (or (re-matches #"\/" tokkentype) (re-matches #"\*" tokkentype)) (if (isAFactor(linetravel aline thecount))  (spit "result.txt" "ACCEPT" :append true) (print "REJECT, * | / not followed by FACTOR"))))
          )

          )


  )(recur (inc thecount) (let [tokkentype tokkentype] )))
    )
(with-open [r (reader "input.txt")]
   (doseq [line (line-seq r)]

      (spit "results.txt" (grammarCHECK line))
      (spit "output.txt" (str (join "\n" (split line #"\s+")) "\n") :append true)
   )
)
(spit "tokken.txt" (reduce conj #{} (line-seq (io/reader "output.txt"))) :append true)    ;;ended up not using tokkens to make a parse table
;;We now have a set of each lexeme from the input, no duplicates. We need to match these to tokkens
;;17 possible tokkens in given input file