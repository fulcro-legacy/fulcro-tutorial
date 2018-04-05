(defproject fulcrologic/fulcro-tutorial "1.0.0-SNAPSHOT"
  :description "An interactive tutorial for Fulcro"
  :url ""
  :license {:name "MIT"
            :url  "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0" :scope "provided"]
                 [com.stuartsierra/component "0.3.2"]
                 [org.clojure/clojurescript "1.9.946" :scope "provided"]
                 [fulcrologic/fulcro "2.4.4"]
                 [fulcrologic/fulcro-inspect "2.0.0" :exclusions [fulcrologic/fulcro fulcrologic/fulcro-css]]
                 [cljsjs/d3 "3.5.7-1"]
                 [hickory "0.7.1"]
                 [devcards "0.2.4" :exclusions [org.clojure/clojure cljsjs/react cljsjs/react-dom]]
                 [cljsjs/codemirror "5.8.0-0"]]

  :source-paths ["src/dev" "src/tutorial"]
  :resource-paths ["resources"]

  :jvm-opts ["-XX:-OmitStackTraceInFastThrow" "-Xmx1024m" "-Xms512m"]
  :clean-targets ^{:protect false} ["resources/public/js" "target"]

  :cljsbuild {:builds
              [{:id           "tutorial"
                :figwheel     {:devcards true}
                :source-paths ["src/tutorial"]
                :compiler     {:main           fulcro-tutorial.main
                               :asset-path     "js/tutorial"
                               :devcards       true
                               :output-to      "resources/public/js/tutorial.js"
                               :output-dir     "resources/public/js/tutorial"
                               :preloads       [devtools.preload]
                               :parallel-build true
                               :foreign-libs   [{:provides ["cljsjs.codemirror.addons.closebrackets"]
                                                 :requires ["cljsjs.codemirror"]
                                                 :file     "resources/public/codemirror/closebrackets-min.js"}
                                                {:provides ["cljsjs.codemirror.addons.matchbrackets"]
                                                 :requires ["cljsjs.codemirror"]
                                                 :file     "resources/public/codemirror/matchbrackets-min.js"}]}}
               {:id           "tutorial-live"
                :source-paths ["src/tutorial"]
                :compiler     {:main           fulcro-tutorial.main
                               :devcards       true
                               :asset-path     "js"
                               :output-to      "docs/js/tutorial.js"
                               :output-dir     "resources/public/js/tutorial-live"
                               :parallel-build true
                               :optimizations  :advanced
                               :foreign-libs   [{:provides ["cljsjs.codemirror.addons.closebrackets"]
                                                 :requires ["cljsjs.codemirror"]
                                                 :file     "resources/public/codemirror/closebrackets-min.js"}
                                                {:provides ["cljsjs.codemirror.addons.matchbrackets"]
                                                 :requires ["cljsjs.codemirror"]
                                                 :file     "resources/public/codemirror/matchbrackets-min.js"}]}}
               ]}

  :profiles {:dev {:repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :dependencies [[binaryage/devtools "0.9.9"]
                                  [org.clojure/tools.namespace "0.3.0-alpha4"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [com.cemerick/piggieback "0.2.2"]
                                  [figwheel-sidecar "0.5.15"]]}})
