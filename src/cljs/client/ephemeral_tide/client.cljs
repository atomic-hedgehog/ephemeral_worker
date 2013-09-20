(ns ephemeral-worker.client
  (:require [ajax.core :as ajax]))

(defn log [message]
  (.log js/console (str "[worker] " message)))


(defn handler [response]
  (.log js/console (str response)))

(defn error-handler [error]
  (.log js/console (str "ERROR: " error)))

;; pulse code
(def worker-status (atom {:worker-state :not-started
                          :uptime 0
                          :start-time (.now js/Date)}))
(defn update-uptime [status-atom]
  (swap! status-atom merge {:uptime (- (.now js/Date) (:start-time @status-atom))}))

(defn pulse []
  (update-uptime worker-status)
  (ajax/GET "/" {:params @worker-status
                 :handler handler
                 :error-handler error-handler})
  (js/setTimeout pulse 1000))

(pulse)

(defn on-worker-message [event]
  (swap! worker-status merge {:worker-state (.-data event)}))

(def worker (js/Worker. "js/worker.js"))
(set! (.-onmessage worker) on-worker-message)
(comment .postMessage worker "start!")

