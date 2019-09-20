(ns future-app.db
  (:require [clojure.spec.alpha :as s]))

;; spec of app-db
(s/def ::greeting string?)
(s/def ::app-db
  (s/keys :req-un [::greeting]))

;; initial state of app-db
(def app-db {
  :greeting "Hello Clojure in iOS and Android!"
  :data
  [{:name "Back Squat" :variation "w/ 5 sec pause"
   :values
    [{:value 250 :unit "lbs" :metric :weight}
    {:value 5 :metric :reps}
    {:value 3 :metric :sets}]}
  {:name "Deadlift"
   :values
    [{:value 500 :unit "lbs" :metric :weight}
    {:value 2 :metric :reps}
    {:value 5 :metric :sets}]}]
  })
