(ns rb-martian-robots.core-test
  (:require [clojure.test :refer :all]
            [rb-martian-robots.core :refer :all]))



(def input-string
  "5 3\n1 1 E\nRFRFRFRF\n\n3 2 N\nFRRFLLFFRRFLL\n\n0 3 W\nLLFFFLFLFL")

(def output-string
  "1 1 E\n3 3 N LOST\n2 3 S")

(def expected-parsed-input
  {:world-size [5 3],
   :robots [{:position [1 1], :orientation "E", :instructions ["R" "F" "R" "F" "R" "F" "R" "F"]}
            {:position [3 2], :orientation "N", :instructions ["F" "R" "R" "F" "L" "L" "F" "F" "R" "R" "F" "L" "L"]}
            {:position [0 3], :orientation "W", :instructions ["L" "L" "F" "F" "F" "L" "F" "L" "F" "L"]}]})

(deftest parse-tests
  (testing "that we can parse the input string"
    (is (= expected-parsed-input
           (parse-input input-string))))
  (testing "minimal valid input works"
        (is (= (parse-input "3 4")
               {:world-size [3 4], :robots []})))
  (testing "robots with no instruction input work"
    (is (= (parse-input "3 4\n1 1 E\n")
           {:world-size [3 4],
            :robots [{:position [1 1] :orientation "E" :instructions []}]}))
    (is (= (parse-input "3 4\n1 1 E\n\n\n5 5 S\nF")
           {:world-size [3 4],
            :robots [{:position [1 1] :orientation "E" :instructions []}
                     {:position [5 5] :orientation "S" :instructions ["F"]}]}))))



; (run-tests)

(comment
  (use 'clojure.data)
  (diff expected-parsed-input
     (parse-input input-string)))





