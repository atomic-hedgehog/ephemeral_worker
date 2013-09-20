(ns ephemeral-worker.server
  (:require
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as response])
  (:use [hiccup.core]
        [compojure.core]))

(defn view-layout [& content]
  (html
      [:head
           [:meta {:http-equiv "Content-type"
                        :content "text/html; charset=utf-8"}]
           [:title "{{name}}"]]
      [:body content]))
 
(defn view-content []
  (view-layout
    [:h2 "Web RTC Test"]
    [:textarea {:id "dataChannelSend" :disabled "disabled"}]
    [:textarea {:id "dataChannelReceive" :disabled "disabled"}]
    [:div 
     [:button {:id "startButton"} "Start"]
     [:button {:id "sendButton"} "Send"]
     [:button {:id "closeButton"} "Stop"]]
    [:script {:src "/js/client.js"}]))

(defroutes main-routes
  (GET "/" []
      (view-content))
      (route/resources "/"))
 
(def app (handler/site main-routes))
