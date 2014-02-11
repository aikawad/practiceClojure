;===========================================================
;  7.2 第一天：訓練Luke

(println "Give me Some Clojure!")

; 7.2.1 調用基本函數
(- 1)
(+ 1 1)
(* 10 10 )

(/ 1 3)
(/ 2 4)
(/ 2.0 4)
(class (/ 1 3))

(mod 5 4)

(/ (/ 12 2) (/ 6 2))
(+ 2 2 2 2)

(- 8 1 2)
(/ 8 2 2)

; 排序檢查
(< 1 2 3)
(< 1 3 2 4)

; 類型轉換
(+ 3.0 5 )
(+ 3 5.0 )

;===========================================================
; 7.2.2
(println "hi breakline \n 2nd line. and more\r\n 3rd line. and more")
; 輸出在 Console畫面
(str  1)
(str "yoda, " "lucas, " "darth")
(str "One: "  1 " Two: " 2)
(str \A \e \i)
(= "a" \a)
(=  (str \a) "a" )

;===========================================================
; 7.2.3  布爾值 與 表達式
(= 1 1.0)
(= 1 2)
(< 1 2)
(class true)
(class(= 1 2))

(if true (println "True it is."))
(if (> 1 2) (println "True it is."))

;line break

(if (< 1 2)
  (println "False it is not."))


(if  false
  (println "true") (println "false" ))


; Clojure裡面 什麼東西會取值 "nil"
(first ())

(if 0 (println "true"))
(if nil (println "true"))
(if ""  (println "true"))


; 7.2.4 列表 映射表 集合 向量

; 創建列表資料
(list 1 2 3)
'(1 2 3)

(first '(:r2d2, :c3po))
(last '(:r2d2, :c3po))
(rest '(:r2d2, :c3po))
(cons  :battle=droid '(:r2d2, :c3po))(cons  :battle=droid '(:r2d2, :c3po))

; Vector
[:hutt :wokie :ewok]
(first [:hutt :wokie :ewok])
(nth [:hutt :wokie :ewok] 2)
(nth [:hutt :wokie :ewok] 0)
(last [:hutt :wokie :ewok])
([:hutt :wokie :ewok] 2)

(concat [:darth-vader] [:darth-maul])
(rest [:hutt :wokie :ewok])

; Set
#{:x-wing :y-wing :tie-fighter}

(def spacecraft #{:x-wing :y-wing :tie-fighter})
spacecraft
(count spacecraft)
(sort spacecraft)

(sorted-set 2 3 1)

(clojure.set/union #{:skywalker} #{:vader})
(clojure.set/difference #{1 2 3} #{2})

(#{:jar-jar :chewbacca} :chewbacca)
(#{:jar-jar :chewbacca} :luke)

; Map
{:chewie :wookie :lea :human}
; 下面的map是奇數個
; {:jabba :hun :han}

{:darth-vader "obi wan", :luke "yoda"}

(def mentors {:darth-vader "obi wan", :luke "yoda"})
(mentors :luke)
(:luke mentors )

(merge {:x-wing 2, :y-wing 4 } { :tie-fighter 2} )
(merge-with + {:y-wing 2, :x-wing 4 } { :tie-fighter 2, :x-wing 3} )

(assoc {:one 1} :two 2)

(sorted-map 1 :one, 3 :three, 2 :two)

; 7.2.5 定義函數

(defn force-it [] (str "Use the force," "Luke."))
(force-it)

(defn force-it2
  "The first function a young Jedi xxxx"
  []
  (str "Use the force," "Luke."))
(force-it2)

(doc force-it2)


(defn force-it3
  "The first function a young Jedi xxxx"
  [jedi]
  (str "Use the force," jedi ))

(force-it3 "Luke")

(doc str)


; 7.2.6 綁定

(def line [[0 0] [10 20]])
line

(defn line-end [ln] (last ln))
(line-end line)

(defn line-end2 [[_ second]]  second)
(line-end2 line)

(def board [[:x :o :x] [:o :x :o ] [:o :x :o ] ])

(defn center [[_ [_ c _] _]] c)
(center board)

(defn center2 [[_ [_ c]]] c)
(center2 board)

; 使用let 語法
(defn center3 [board]
  (let [[_ [_ c]] board] c))
(center3 board)


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
