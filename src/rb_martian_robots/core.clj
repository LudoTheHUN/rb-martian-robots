(ns rb-martian-robots.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


(defn -main
  "Application entry point"
  [& args]
  (println "foo" args))


; Lets read the input.robots file...
(defn read-robots-file [filepath]
  (slurp filepath))

(def lines-per-robot 3)

(defn empty-str? [x]
  (= x ""))

(defn read-x-y [line]
  (vec (map #(Integer. %) (take 2 (str/split line #" ")))))

(defn read-robot-lines
  "Breaks the robot lines into per robot vector"
  [robot-instruction-lines]
  (partition lines-per-robot lines-per-robot [""] robot-instruction-lines))

(defn parse-robot-vector [robot-vector]
  "Parse a single robot vector into a data representation"
  {:position (read-x-y (first robot-vector))
   :orientation (str (last (first robot-vector)))
   :instructions (filter #(not (empty-str? %)) (str/split (second robot-vector) #""))})

(defn parse-input [s]
  "prase the input string into a data representation"
  (let [lines (str/split-lines s)
        world-size (read-x-y (first lines))
        per-robot-vectors (read-robot-lines (rest lines))]
    {:world-size world-size
     :robots (vec (map parse-robot-vector per-robot-vectors))}))


(comment ; REPL time test runs
  (parse-input (read-robots-file "input.robots"))
  (read-robot-lines (rest (str/split-lines (read-robots-file "input.robots"))))
  (read-robots-file "output.robots"))
