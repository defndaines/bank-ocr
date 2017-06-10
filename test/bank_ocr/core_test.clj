(ns bank-ocr.core-test
  (:require [clojure.test :refer :all]
            [bank-ocr.core :refer :all]
            [clojure.string :as string]))

(deftest user-story-1-test
  (testing "Use cases from the original document."
    (is (= "000000000"
           (apply str (parse [" _  _  _  _  _  _  _  _  _ "
                              "| || || || || || || || || |"
                              "|_||_||_||_||_||_||_||_||_|"]))))
    (is (= "111111111"
           (apply str (parse [""
                              "  |  |  |  |  |  |  |  |  |"
                              "  |  |  |  |  |  |  |  |  |"]))))
    (is (= "222222222"
           (apply str (parse [" _  _  _  _  _  _  _  _  _ "
                              " _| _| _| _| _| _| _| _| _|"
                              "|_ |_ |_ |_ |_ |_ |_ |_ |_ "]))))
    (is (= "333333333"
           (apply str (parse [" _  _  _  _  _  _  _  _  _ "
                              " _| _| _| _| _| _| _| _| _|"
                              " _| _| _| _| _| _| _| _| _|"]))))
    (is (= "444444444"
           (apply str (parse [""
                              "|_||_||_||_||_||_||_||_||_|"
                              "  |  |  |  |  |  |  |  |  |"]))))
    (is (= "555555555"
           (apply str (parse [" _  _  _  _  _  _  _  _  _ "
                              "|_ |_ |_ |_ |_ |_ |_ |_ |_ "
                              " _| _| _| _| _| _| _| _| _|"]))))
    (is (= "666666666"
           (apply str (parse [" _  _  _  _  _  _  _  _  _ "
                              "|_ |_ |_ |_ |_ |_ |_ |_ |_ "
                              "|_||_||_||_||_||_||_||_||_|"]))))
    (is (= "777777777"
           (apply str (parse [" _  _  _  _  _  _  _  _  _ "
                              "  |  |  |  |  |  |  |  |  |"
                              "  |  |  |  |  |  |  |  |  |"]))))
    (is (= "888888888"
           (apply str (parse [" _  _  _  _  _  _  _  _  _ "
                              "|_||_||_||_||_||_||_||_||_|"
                              "|_||_||_||_||_||_||_||_||_|"]))))
    (is (= "999999999"
           (apply str (parse [" _  _  _  _  _  _  _  _  _ "
                              "|_||_||_||_||_||_||_||_||_|"
                              " _| _| _| _| _| _| _| _| _|"]))))
    (is (= "123456789"
           (apply str (parse ["    _  _     _  _  _  _  _ "
                              "  | _| _||_||_ |_   ||_||_|"
                              "  ||_  _|  | _||_|  ||_| _|"]))))))


(deftest user-story-2-test
  (testing "Check valid checksums of account numbers."
    (is (not (status '(4 5 7 5 0 8 0 0 0)))))
  (testing "Check invalid checksums of account numbers."
    (is (= "ERR" (status '(6 6 4 3 7 1 4 9 5))))
    (is (= "ILL" (status '(8 6 1 1 0 \? \? 3 6))))))


(deftest user-story-3-test
  (testing "Use cases from the original document."
    (is (= "000000051"
           (apply str (parse [" _  _  _  _  _  _  _  _    "
                              "| || || || || || || ||_   |"
                              "|_||_||_||_||_||_||_| _|  |"]))))
    (is (= "49006771?"
           (apply str (parse ["    _  _  _  _  _  _     _ "
                              "|_||_|| || ||_   |  |  | _ "
                              "  | _||_||_||_|  |  |  | _|"]))))
    (is (= "1234?678?"
           (apply str (parse ["    _  _     _  _  _  _  _ "
                              "  | _| _||_| _ |_   ||_||_|"
                              "  ||_  _|  | _||_|  ||_| _ "]))))))
