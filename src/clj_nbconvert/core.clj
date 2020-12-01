(ns clj-nbconvert.core
  (:require [clj-nbconvert.option :as option]
            [clj-nbconvert.utils :refer [maybe-comp]]
            [clj-nbconvert.output :refer [output-with-config]]
            [clj-nbconvert.file.ipynb :refer [get-ipynb-files]]
            [clj-nbconvert.convert :refer [convert-to result-with-log]]))

(def convert-pipeline
  (maybe-comp
   output-with-config
   :file
   result-with-log
   (partial convert-to :html)))

(defn -main []
  (let [ipynb (get-ipynb-files (option/config :input-path))]
    (doseq
     [converted (pmap convert-pipeline ipynb)]
      (println "Output :" (.toString converted)))
    (shutdown-agents)))
