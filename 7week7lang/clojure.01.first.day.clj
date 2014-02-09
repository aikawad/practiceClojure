; 7.2.5
(defn force-it [] (str "Use the force," "Luke."))
(force-it)

(defn force-it
  "The first function a young Jedi needs"
  []
  (str "Use the force," "Luke."))
(doc force-it)


(defn force-it
  "The first function a young Jedi needs"
  [jedi]
  (str "Use the force," jedi))
(force-it "Luke")

(doc str)


; 7.2.6

(def person {:name "Jabba" :profession "Gangster"})
(let [{name :name} person] (str "The person's name is " name))

(def villains [{:name "Godzilla" :size "big"} {:name "Ebola" :size "small"}])
(let [[_ {name :name}] villains] (str "Name of the second villain: " name))

;7.2.7

(def people ["Lea", "Han Solo"])
(count "Lea")
(map count people)

(defn twice-count [w] (* 2 (count w)))
(twice-count "Lando")
(map twice-count people)

(map (fn [w] (* 2 (count w)))people)
(map #(* 2 (count %)) people)

(def v [3 1 2])
(apply + v)
(apply max v )

(filter odd? v)
(filter #(< % 3) v)

; 第一天自習


; q1. 使用Clojure序列的例子。
; q2. Clojure函數的正式定義。
; q3. 用於在你的環境中啟動repl的腳本。
; q4. 實現一個函數(big st n), 當字符串st長度不超過n個字符時返回true。
; q5. 實現一個函數(collection-type col),根據給定集合col的類型返回: list, : map 或:vector。

; a1. 在官方sequence文檔的 The Seq library段落會找到一堆 primary sequence function ，
;     文檔網址 http://clojure.org/sequences
;     分為三類目標
;     A. Seq in, Seq out
;     B. Using a seq
;     C. Creating a seq
; 一些範例
(def q1seq [3 1 2])
(rand-nth q1seq) ; 每次執行會不一樣
(empty q1seq)
(empty? q1seq)
(not-empty q1seq)

; a2. 官方網站上的 defn文檔 
; ------------------------------------------------------
; defn
; macro
;
; Usage: (defn name doc-string? attr-map? [params*] prepost-map? body)
;        (defn name doc-string? attr-map? ([params*] prepost-map? body) + attr-map?)
;
; Same as (def name (fn [params* ] exprs*)) or (def
; name (fn ([params* ] exprs*)+)) with any doc-string or attrs added
; to the var metadata. prepost-map defines a map with optional keys
; :pre and :post that contain collections of pre or post conditions.
;
; Added in Clojure version 1.0
; ------------------------------------------------------

; a3. 我用light table 開啟這個clj的腳本檔案, 然後在light table的本檔案啟動repl的執行

; a4. 
(defn big 
  "當字符串st長度不超過n個字符時返回true。"
  [st, n] (<=  (count st) n) )
(big  "abc" 4)
(big  "abcd" 4)
(big  "abcde" 4)

; a5.
(defn collection-type 
  [col]
  ( type col ))

(collection-type (list 1 2))         
(collection-type [2 1])              
(collection-type { :yuda 1, :luke 3})
  
(type (list 1 2)) 
(type [2 1])
(type { :yuda 1, :luke 3})
  
  
