(ns pronto-server.core
    (:require [clojure.java.shell :refer [sh]]
              [ring.middleware.json :refer [wrap-json-params]]
              [ring.util.response :refer [response]]
              [environ.core :refer [env]]
              [clojure.pprint :refer (pprint)]
              [ring.adapter.jetty :refer [run-jetty]]
              [compojure.core :refer :all]
              [compojure.route :as route]
              ))

(defn -main
  [& args])


(defn handler [handler]
  (fn [request]
    (let [pr-number (get-in request [:params "pull_request" "number"])]
    #_(sh "./pronto-exec.sh")
    (sh "bundle" "exec" "pronto" "run" "-f" "github_pr" "-c" "origin/master"
        :dir (env :repository-path)
        :env {"GITHUB_ACCESS_TOKEN" (env :github-access-token)
              "PULL_REQUEST_ID" pr-number})
    (let [response (handler request)]
      response))))

(def app
  (-> {:status 200}
      handler
      wrap-json-params))

;(defroutes app
;           (GET "/" [] pr-hook))

(defonce server (ring.adapter.jetty/run-jetty #'app {:port 8080 :join? false}))

; TODO: call pronto with sh
; TODO: set env variables
; TODO: logs using [ch.qos.logback/logback-classic "1.1.2"] [org.clojure/tools.logging "0.3.1"] [org.slf4j/slf4j-api "1.7.10"]
; TODO: start server from main (port?)
; TODO: add compujre