(ns clj-nbconvert.core
  (:require [clojure.java.io :as io]
            [clj-nbconvert.option :as option]
            [clj-nbconvert.utils :refer [success-comp]]
            [clj-nbconvert.output :refer [output-with-config]]
            [clj-nbconvert.file.ipynb :refer [get-ipynb-files]]
            [clj-nbconvert.convert :refer [convert-to result-with-log]]))

(def convert-pipeline
  (success-comp
   (partial convert-to :html)
   result-with-log
   :file
   output-with-config))

(defn -main []
  (let [ipynb (get-ipynb-files (option/config :input-path))]
    (doseq
     [converted (pmap convert-pipeline ipynb)]
      (println "Output :" (.toString converted)))
    (shutdown-agents)))
