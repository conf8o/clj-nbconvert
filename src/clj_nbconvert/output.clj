(ns clj-nbconvert.output
  (:require [clojure.java.io :as io]
            [clj-nbconvert.option :as option]
            [clj-nbconvert.file.utils :refer [count-path drop-path]]))

(defn make-output-base
  "Makes function to output source based with paths of arguments.
  ex)
  When you set arguments below
  input-base-path: /aaa/bbb/ccc, output-base: /xxx/yyy
  You get a function to copy source from input path to output path such as the following.
  source: /aaa/bbb/ccc/ddd/eee/foo.txt
  output: /xxx/yyy/ddd/eee/foo.txt"
  [input-base-path output-base-path]
  (let [input-base-path-count (count-path input-base-path)]
    (fn [source]
      (let [output-file (->> source
                             .toPath
                             (drop-path input-base-path-count)
                             .toString
                             (io/file output-base-path))]
        (io/make-parents output-file)
        (io/copy source output-file)
        output-file))))

(defn output-with-config [source]
  (let [{input :input-path output :output-path} option/config]
    ((make-output-base input output) source)))
