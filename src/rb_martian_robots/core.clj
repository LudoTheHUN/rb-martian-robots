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
     :robots (vec (map parse-robot-vector per-robot-vectors))
     :scents #{}
     :done-robots []}))

(defn rotation [orientation instruction]
    (case [orientation instruction]
      ["N" "R"] "E"
      ["N" "L"] "W"
      ["E" "R"] "S"
      ["E" "L"] "N"
      ["S" "R"] "W"
      ["S" "L"] "E"
      ["W" "R"] "N"
      ["W" "L"] "S"
      orientation))

(defn rotate-robot [robot]
  (update-in robot [:orientation] #(rotation % (first (:instructions robot)))))

(defn move-robot [robot world]
  (if (= (first (:instructions robot)) "F")
    (update-in robot [:position]
               (fn [[x y]]
                 (case (:orientation robot)
                   "N" [x (+ y 1)]
                   "E" [(+ x 1) y]
                   "S" [x (- y 1)]
                   "W" [(- x 1) y])))
    ;;TODO handle scent logic here from world
    robot))



(defn drop-instruction [robot]
  (update-in robot [:instructions] #(vec (rest %))))


(move-robot {:position [3 4] :orientation "E" :instructions ["F"]} {})






;;TODO tests
(reduce rotation "N" ["R" "R" "R" "R"])
(reduce rotation "N" ["S" "S" "S" "S"])



(defn tick-robot
  "Ticks through one robot instruction given a world and a robot, returns ticked robot"
  [robot world]
  (-> robot
      rotate-robot
      (move-robot world)
      drop-instruction))

(defn off-world?
  "Test if a robot has falen off the world"
  [robot world]
  (let [[x y] (:position robot)
        [wx wy] (:world-size world)]
      (or (< x 0) (< y 0) (> x wx) (> y wy))))

(defn do-robot [start-robot world]
  "does all robot instructions, return final robot"
  (loop [robot start-robot]
    (println robot)  ;; TODO remove
    (let [ticked-robot (tick-robot robot world)]
      (cond
        (empty? (:instructions robot))
        robot

        (off-world? ticked-robot world)
        (if (contains? (:scents world) (:position robot))
            (recur (conj ticked-robot {:position (:position robot)}))
          (conj robot {:lost? true}))

        true (recur ticked-robot)))))


(defn tick-world
  "takes a world and returns new world given a robot"
  [world robot]
  (let [robot (do-robot robot world)]
    (if (:lost? robot)
      (-> world
          (update-in [:done-robots] #(conj % robot))
          (update-in [:scents] #(conj % (:position robot))))
      (update-in world [:done-robots] #(conj % robot)))))  ;; TODO Not dry enough.

(defn do-world [world]
  (let [robots (:robots world)]
    (reduce tick-world world robots)))




;; TODO lost? world update
;; TODO world loop

(comment ; REPL time test runs
  (parse-input (read-robots-file "input.robots"))
  (read-robot-lines (rest (str/split-lines (read-robots-file "input.robots"))))
  (read-robots-file "output.robots"))



(def a-world
  (parse-input (read-robots-file "input.robots")))

(def a-robot
  (-> a-world
      :robots
      second))

a-robot
(tick-robot a-robot a-world)
(do-robot a-robot a-world)
(do-world a-world)

;;TODO tests











