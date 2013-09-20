(defproject ephemeral_worker "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1806"]
                 [compojure "1.1.5"]
                 [cljs-ajax "0.2.0"]
                 [hiccup "1.0.4"]]
  :plugins [[lein-cljsbuild "0.3.2"]
            [lein-ring "0.8.7"]]
  :source-paths ["src" "src/clj"]
  :cljsbuild {:builds
              [{:source-paths ["src/cljs/client"],
                :id "main",
                :compiler
                {:pretty-print true,
                 :output-to "resources/public/js/client.js",
                 :optimizations :simple}}
               
               {:source-paths ["src/cljs/worker"],
                :id "main",
                :compiler
                {:pretty-print true,
                 :output-to "resources/public/js/worker.js",
                 :optimizations :simple}}]}
  
  :main ephemeral-worker.server
  :ring {:handler ephemeral-worker.server/app})
