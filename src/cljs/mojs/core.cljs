(ns mojs.core
    (:require [reagent.core :as reagent]))

(defn translate-y [node]
  (fn [progress]
    (set! (-> node .-style .-transform)
     (str "translateY(" (* 200 progress) "px)"))))

(defn animation-did-mount [this]
  (.log js/console js/mojs)
  (.log js/console js/mojs.Tween)
  (.run
   (js/mojs.Tween.
    (clj->js
     {:repeat 999
      :delay 2000
      :onUpdate (translate-y (reagent/dom-node this))}))))

(defn animation []
  (reagent/create-class {:render (fn [] [:div.square])
                         :component-did-mount animation-did-mount}))

(defn ^:export main []
  (reagent/render [animation]
                  (.getElementById js/document "app")))

