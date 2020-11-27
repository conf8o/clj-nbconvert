(ns clj-nbconvert.option)

(def config 
  {:input-path "." :output-path "."})

(def windows-os? (clojure.string/includes? (System/getProperty "os.name") "Windows"))
