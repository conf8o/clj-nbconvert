(ns clj-nbconvert.file.ipynb-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [clj-nbconvert.file.ipynb :refer :all]))

(deftest ipynb?-test
  (testing "nil"
    (is (not (ipynb? nil))))
  (testing "String"
    (are [s] (ipynb? s)
         ".ipynb"
         "0.ipynb")
    (are [s] (not (ipynb? s))
         ".ipyn"
         ".ipynb0"
         ""))
  (testing "File"
    (are [s] (ipynb? (io/file s))
         ".ipynb"
         "0.ipynb")
    (are [s] (not (ipynb? (io/file s)))
         ".ipyn"
         ".ipynb0"
         ""))
  (testing "Path"
    (are [s] (ipynb? (.toPath (io/file s)))
         ".ipynb"
         "0.ipynb")
    (are [s] (not (ipynb? (.toPath (io/file s))))
         ".ipyn"
         ".ipynb0"
         "")))
