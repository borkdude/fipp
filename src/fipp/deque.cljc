(ns fipp.deque
  "Double-sided queue built on rrb vectors."
  (:refer-clojure :exclude [empty concat])
  #?@(:bb [] :default [(:require [clojure.core.rrb-vector :as rrb])]))

(def create vector)

(def empty [])

(defn popl [v]
  (subvec v 1))

(def conjr (fnil conj empty))

(defn conjlr [l deque r]
  #?(:bb (reduce into [l] [deque [r]])
     :default (rrb/catvec [l] deque [r])))

(def concat #?(:bb into :default rrb/catvec))
