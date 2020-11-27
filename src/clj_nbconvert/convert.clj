(ns clj-nbconvert.convert
  (:require [clojure.java.shell :refer [sh]]
            [clojure.string :as string]
            [clj-nbconvert.option :as option]
            [clj-nbconvert.file.ipynb :refer [ipynb-pattern]]))

(def jupyter "jupyter")
(def -- {:to "--to"})

(def supported-format {:html "html"})

(defn convert-to [format- notebook]
  (when-let [format (supported-format format-)]
      (println "Converting to" format ":" notebook)
      (conj
        (if option/windows-os?
          (sh "cmd" "/c" (string/join " " [jupyter "nbconvert" (-- :to) format notebook]))
          (sh jupyter "nbconvert" (-- :to) format notebook))
        {:formatted (string/replace notebook ipynb-pattern (str "." format))})))
