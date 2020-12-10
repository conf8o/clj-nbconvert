(ns clj-nbconvert.option
  (:require [clj-nbconvert.file.utils :as file-utils]))

(def config (load-file (file-utils/resource "config.clj")))

(def windows-os? (clojure.string/includes? (System/getProperty "os.name") "Windows"))
