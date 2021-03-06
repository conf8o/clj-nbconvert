(defproject clj-nbconvert "0.1.0-SNAPSHOT"
  :description "Converts jupyter notebooks to html format documents with \"jupyter nbconvert --to\" command."
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :main clj-nbconvert.core
  :resource-paths ["resources"]
  :repl-options {:init-ns clj-nbconvert.core})
