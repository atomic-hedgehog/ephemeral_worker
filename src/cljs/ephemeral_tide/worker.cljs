(ns ephemeral-worker.worker)

(defn log [message]
  (.log js/console (str "[worker] " message)))

(def starting-state (atom :not-started))

(defmulti transition identity)

(defmethod transition :not-started []
  :started)

(defmethod transition :started []
  :running)

(defmethod transition :running []
  :finished)

(defmethod transition :default
  :finished)

(defn still-running? [state]
  (not= state :finished))

(defn process-current-state [state]
  (transition state))

(defn work [state]
  (while (still-running? @state)
      (log (str @state))
      (swap! state process-current-state)))

(work starting-state)
