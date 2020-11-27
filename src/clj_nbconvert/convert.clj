(ns clj-nbconvert.convert
  (:use [clojure.java.shell :only [sh]])
  (:require [clojure.string :as string]
            [clj-nbconvert.option :as option]))

(def converting-command "jupyter nbconvert --to")

(def supported-format {:html "html"})

(defn convert-to [format- notebook]
  (when-let [format (supported-format format-)]
    (let [command (string/join " " [converting-command format notebook])]
      (println "Converting to" format ":" notebook)
      (conj
        (if option/windows-os?
          (sh "cmd" "/c" command)
          (sh command))
        {:notebook notebook}))))
