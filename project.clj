(defproject bank-ocr "0.3.0-SNAPSHOT"
  :description "Clojure implementation of Bank OCR kata."
  :url "https://github.com/defndaines/bank-ocr"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]]
  :aot [bank-ocr.core]
  :uberjar-name "bank-ocr.jar"
  :main bank-ocr.core)
