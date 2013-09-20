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
       [:h2 "Project-Name"]
       [:p {:id "clickhere"} "Get yourself a nice alert by clicking here."]
       [:script {:src "/js/jquery-1.10.2.min.js"}]
       [:video]
       [:script {:src "/js/cljs.js"}]))

(defroutes main-routes
  (GET "/" []
      (view-content))
      (route/resources "/"))
 
(def app (handler/site main-routes))
