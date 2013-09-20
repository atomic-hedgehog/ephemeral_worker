(ns ephemeral-worker.client
  (:require [ajax.core :as ajax]))
 
(defn handler [response]
  (.log js/console (str response)))

(defn error-handler [error]
  (.log js/console (str "ERROR: " error)))

(defn pulse []
  (ajax/GET "/" {:handler handler
                 :error-handler error-handler})
  (js/setTimeout pulse 1000))

(pulse)


