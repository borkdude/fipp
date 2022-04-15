(ns fipp.ednize.instant
  "Provides features that may not be available under every Clojure / JVM combination."
  (:require [fipp.ednize :refer [IEdn]])
  (:import (java.sql Timestamp)
           (java.util Date)
           (java.time.format DateTimeFormatter)
           (java.time ZoneId)))

(set! *warn-on-reflection* true)

(extend-protocol IEdn
  Timestamp
  (-edn [x]
    (let [dt (-> x .toInstant (.atZone (ZoneId/of "GMT")))
          s (.format dt DateTimeFormatter/ISO_LOCAL_DATE_TIME)]
      (tagged-literal 'inst s)))

  Date
  (-edn [x]
    (let [dt (-> x .toInstant (.atZone (ZoneId/of "GMT")))
          s (.format dt (DateTimeFormatter/ofPattern "yyyy-MM-dd'T'HH:mm:ss.SSS-00:00"))]
      (tagged-literal 'inst s))))
