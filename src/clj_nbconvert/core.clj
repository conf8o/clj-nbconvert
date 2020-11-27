(ns clj-nbconvert.core
  (:require [clojure.java.io :as io]
            [clj-nbconvert.option :as option]
            [clj-nbconvert.utils :refer [success-comp]]
            [clj-nbconvert.file.utils :refer [count-path drop-path path-sep]]
            [clj-nbconvert.file.ipynb :refer [get-ipynb-paths]]
            [clj-nbconvert.convert :refer [convert-to]]))

(defn result-with-log 
  "Outputs converting log and returns result if key :exit of result is 0 else nil."
  [format result]
  (if (zero? (:exit result))
    (do (println "Successful converting to" (name format) ":" (:formatted result)) result)
    (println "Failed converting to" (name format) ":" 
                 (:formatted result)
                 (:err result))))

(defn output [source]
    (let [output-path (->> source
                           (drop-path (count-path (option/config :input-path)))
                           (str (option/config :output-path) path-sep))]
      (io/make-parents output-path)
      (io/copy (io/file source) (io/file output-path))
      output-path))

(def convert-pipeline
  (success-comp
   (partial convert-to :html)
   (partial result-with-log :html)
   :formatted
   output))

(defn -main []
  (let [ipynb-paths (get-ipynb-paths (option/config :input-path))]
    (doseq
     [converted (pmap convert-pipeline ipynb-paths)]
      (println "Output :" converted))
    (shutdown-agents)))
