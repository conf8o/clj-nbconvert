(ns clj-nbconvert.convert
  (:require [clojure.java.shell :refer [sh]]
            [clojure.java.io :as io]
            [clojure.string :as string]
            [clj-nbconvert.option :as option]
            [clj-nbconvert.file.ipynb :refer [ipynb-pattern]]))

(def jupyter "jupyter")
(def -- {:to "--to"})

(def supported-format {:html "html"})

(defn convert-to 
  "Does following command. 
   jupyter nbconvert --to [format- (keyword)] [notebook]"
  [format- notebook]
  (when-let [format (supported-format format-)]
    (let [nb-name (.toString notebook)]
      (println "Converting to" format ":" nb-name)
      (conj
        (if option/windows-os?
          (sh "cmd" "/c" (string/join " " [jupyter "nbconvert" (-- :to) format nb-name]))
          (sh jupyter "nbconvert" (-- :to) format nb-name)) 
        {:file (io/file (string/replace nb-name ipynb-pattern (str "." format)))
         :format format}))))

(defn result-with-log 
  "Outputs converting log and returns result if key :exit of result is 0 else nil."
  [result]
  (if (zero? (:exit result))
    (do (println "Successful converting to" (:format result) ":" (.toString (:file result))) result)
    (println "Failed converting to" (:format result) ":" 
                 (.toString (:file result))
                 (:err result))))