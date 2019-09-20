(ns future-app.ios.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            ;;[future-app.ios.router :refer [stack-router]]
            [future-app.events]
            [future-app.subs]))

(def ReactNative (js/require "react-native"))
(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(def textInput (r/adapt-react-class (.-TextInput ReactNative)))

(def logo-img (js/require "./images/cljs.png"))

(defn openEditor []
  (prn "click")
)

(defn formatVal [{:keys [value unit]}]
  (str value " " unit))

(defn format [values]
  (clojure.string/join " x "
  (map (fn [{:keys [value unit]}]
    (str value (when unit (str " " unit)))) values)))

(defn exerciseLabel [{:keys [name variation]}]
  [view [text name]
  (when variation [text variation])])

(defn exerciseValue [{:keys [values]}]
  [text
   {:style {:width 150 :height 20 } :on-press #(openEditor)}
  (format values)])

(defn exercise [exercise]
  [view {:style {:padding-bottom 10 }}
    [exerciseLabel exercise]
    [exerciseValue exercise]])


(defn app-root []
  (let [data @(subscribe [:data])]
    (fn []
      [view {:style {:flex-direction "column" :margin 20 :margin-top 50 }}
       [image {:source logo-img
               :style  {:width 80 :height 80 :margin-bottom 30}}]
       (doall
         (for [ex data]
          ^{:key (:name ex)}
          [exercise ex]))])))

(defn editor []
  [text "edito"]
)


(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "FutureApp" #(r/reactify-component app-root)))
