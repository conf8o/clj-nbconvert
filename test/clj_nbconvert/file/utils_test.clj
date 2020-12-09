(ns clj-nbconvert.file.utils-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [clj-nbconvert.file.utils :refer :all])
  (:import  [java.net URI]))

(deftest in-hidden?-test
  (testing "nil"
    (is (not (in-hidden? nil))))
  (testing "File"
    (are [s] (in-hidden? (io/file s))
         ".a/b/c.txt"
         "a/.b/c.txt"
         "a/b/.c.txt")
    (is (not (in-hidden? (io/file "a/b/c.txt"))))))

(deftest to-uri-test
  (testing "URI"
    (are [path] (= (URI. "file:///a/b%20c") (to-uri path))
         "a/b%20c"
         "a\\b%20c"
         "a/b c")))
