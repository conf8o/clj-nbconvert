(ns clj-nbconvert.file.utils
  (:require [clj-nbconvert.option :as option]))

(defn to-path-string [file]
  (.toString (.toPath file)))

(defn not-in-hidden-dir? [path]
  (not
    (re-find
      (re-pattern (str (if option/windows-os? "\\" "/")
                       "\\\\..*"))
    path)))
