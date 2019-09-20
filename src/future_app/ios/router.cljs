(ns future-app.ios.router
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [future-app.ios.core :refer [app-root editor]]
            [future-app.events]
            [future-app.subs]))

(def ReactNative (js/require "react-native"))
(def react-navigation (js/require "react-navigation"))
(def react-navigation-stack (js/require "react-navigation-stack"))
(def createAppContainer (.-createAppContainer react-navigation))
(def createStackNavigator (.-createStackNavigator react-navigation-stack))

;; apparently pages need to be exported from diff files

(def stack-router
  (createStackNavigator
    {:Home {:screen app-root}
     :Editor {:screen editor}}))
