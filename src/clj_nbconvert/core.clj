(ns clj-nbconvert.core
  (:require [clj-nbconvert.option :as option])
  (:use [clj-nbconvert.file.ipynb :only [get-ipynb-paths]]
        [clj-nbconvert.convert :only [convert-to]]))
           
(defn -main
  [& argv]
  (let [ipynb-paths (get-ipynb-paths (option/config :input-path))]
    (doseq
      [result (pmap #(convert-to :html %) ipynb-paths)]
      (if (zero? (:exit result))
        (println "Successful converting to html :" (:notebook result))
        (println "Failed converting to html :" (:notebook result))))
    (shutdown-agents)))
