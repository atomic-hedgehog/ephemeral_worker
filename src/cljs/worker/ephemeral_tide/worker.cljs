(ns ephemeral-worker.worker)

(defn post-status [status]
  (js/postMessage status))

(def starting-state (atom :not-started))

(defmulti transition identity)

(defmethod transition :not-started []
  :started)

(defmethod transition :started []
  :running)

(defmethod transition :running []
  :finished)

(defmethod transition :default []
  :finished)

(defn still-running? [state]
  (not= state :finished))

(defn process-current-state [state]
  (transition state))

(defn work [state]
  (while (still-running? @state)
    (swap! state process-current-state)
    (post-status @state)))

(work starting-state)

(comment js/postMessage "hi!")
