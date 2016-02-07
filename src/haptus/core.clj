(ns haptus.core
  (:require [fn-fx.render :as render]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


;; Describe the GUI
(def init-state
  {:type           :Stage
   :fn-fx/children #{:root}
   :title          "Hello World!"
   :scene          {:type           :Scene
                    :width          300
                    :height         250
                    :fn-fx/children #{:root}
                    :root           {:type           :StackPane
                                     :fn-fx/children #{:children}
                                     :children       [{:type     :Button
                                                       ;; When this action is fired, provide the tag data
                                                       ;; as part of the event handed to the event handler
                                                       :onAction {:tag :say-hello}
                                                       :text     "Say Hello World"}]}}})



(defn -main []
  (let [c (render/create-root init-state)]
    ;; Attach a handler
    (render/update-handler! c (fn [evt]
                                ;; When we get an event, resize the window
                                (println "wasabi")
                                (render/update! c (update-in init-state [:scene :width] + 10))
                                (println "Hello world! : " evt)))
    (render/show! c)))
