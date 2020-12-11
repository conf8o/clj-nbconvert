(ns clj-nbconvert.output
  (:require [clojure.java.io :as io]
            [clj-nbconvert.file.utils :refer [to-uri]]
            [clj-nbconvert.option :as option]))

(defn make-output-base
  "Makes function to output source based with paths of arguments.
  ex)
  When you set arguments below
  input-base-path: /aaa/bbb/ccc, output-base: /xxx/yyy
  You get a function to copy source from input path to output path such as the following.
  source: /aaa/bbb/ccc/ddd/eee/foo.txt
  output: /xxx/yyy/ddd/eee/foo.txt"
  [input-base-path output-base-path]
  (let [input-base-uri (to-uri input-base-path)]
    (fn [^CharSequence source]
      (let [output-file (->> source
                             to-uri
                             (.relativize input-base-uri)
                             .toString
                             (io/file output-base-path))]
        (io/make-parents output-file)
        (io/copy source output-file)
        output-file))))

(def output-with-config
  (let [{input :input-path output :output-path} option/config]
    (make-output-base input output)))
