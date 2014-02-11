;  Clojure Second Day

; 7.3.1 用loop和recur遞歸

(defn size [v]
 (if (empty? v)
   0
   (inc (size (rest v)))))

 (size [1 2 3])
 (size [1 3])
 (size [])

 ;  (loop [x x-initial-value, y y-initial-value] (do-something-with x y))
   (loop [x 4, y 5] (+ x y))
   (loop [x 1] x)


 ; 尾遞迴寫法
 ; c 變量是 累加器
   (defn size [v]
     (loop [l v, c 0]
       (if (empty? l)
         c
         (recur (rest l) (inc c)))))

(size [6 5 4])

; 不過一般用途上，不太需要用到recur操作，Clojure也提供了一些優秀的遞迴替代技術，如後續的延遲序列。


; 7.3.2 序列

; 說明： 序列包含了所有Clojure的各種容器。也就是封裝了所有的Clojure集合、字符串、文件結構(串流、目錄)、。

[1 2 3]
(rest [1 2 3])


(every? number? [1 2 3 :four])
(some nil? [1 2 nil])

(not-every? odd? [1 3 5])
(not-every? odd? [2 3 5])

(not-any? number? [:one :two :three])

; 修改序列
(def words ["luke" "chewie" "han" "lando"])
(filter (fn [word] (> (count word) 4)) words)

(map (fn [x] (* x x)) [1 1 2 3 5])

(def colors ["red" "blue"])
(def toys ["block" "car"])

(for [x colors] (str "I like " x))

(for [x colors, y toys] (str "I like " x " " y "s"))

(defn small-word? [w] (< (count w) 4))

(for [x colors, y toys, :when (small-word? y)] (str "I like " x " " y "s"))

; reduce

(reduce + [1 2 3 4])

(reduce * [1 2 3 4 5])

(sort [3 1 2 4])

; sort by defn
(defn abs [x] (if (< x 0) (- x) x))
(sort-by abs [-1 -4 3 2])

; 7.3.3 延遲計算

; 1. 用range創建有窮序列

; hint 上限不在區間內
(range 1 10)

; with step "3"
(range 1 10 3)


(range 20)
; 可省略下限,  0 是 default 下限
 (range  -20 -10)
 (range  -10 -20)
 (range  -10 -20 -1)
 ; default setp 是 +1

; 2. 無窮序列和 take

; 使用時,拿掉 註解
; (repeat 1)

; (repeat "Use the force, Luke.")

(take 3 (repeat "Use the force, Luke.") )

(take 5 (cycle [:lather :rinse :repeat] ))

(take 5 (drop 2 (cycle [:lather :rinse :repeat] )))

; interleave 操作

(take 20 (interleave (cycle (range 2)) (cycle (range 3))))
(take 20 (interleave ; 用不同的數字比較容易理解
          (cycle (range 7 9))
          (cycle (range 1 4))))

(take 20 (interleave ;另外加的  變成第次跟三組range要資料
          (cycle (range 7 9))
          (cycle (range 5 7))
          (cycle (range 1 4))))

; interleave 另一種操作

(take 5 (iterate inc 1))
(take 5 (iterate dec 0))

; 費伯納係數
(defn fib-pair [[a b]][b (+ a b)])
(fib-pair [3 5])

; 太多了 ,執行時在解開註解
; (iterate fib-pair [1 1])

(take 5
      ( map first
        (iterate fib-pair [1 1])))
(nth
      ( map first
        (iterate fib-pair [1 1])) 90)
        ; (iterate fib-pair [1 1])) 500) ; 500太大，改90

; 階乘
(defn factorial [n] (apply * (take n (iterate inc 1))))
(factorial 5)

; 7.3.4 defrecord 和 protocol

; 定義Clojure協議
(defprotocol Compass
  (direction [c])
  (left [c])
  (right [c]))

(def directions [:north :east :south :west])

(defn turn
  [base amount]
  (rem (+ base amount) (count directions)))

(turn 1 1)
(turn 3 1)
(turn 2 3)

(defrecord SimpleCompass [bearing]
  Compass
(direction [_] (directions bearing))
(left [_] (SimpleCompass. (turn bearing 3)))
(right [_] (SimpleCompass. (turn bearing 1)))
Object
(toString [this] (str "[" (direction this) "]")))

(def c (SimpleCompass. 0))
(left c)
c
(:bearing c)
; 7.3.5 宏 Macro

; 壞掉的unless
(defn unless [test body] (if (not test) body))
(unless true (println "Danger, danger Will Robinson"))

(macroexpand ''something-we-do-not-want-interpreted)
(macroexpand '#(count %))
(fn* [p1__97] (count p1__97))

(defmacro unless [test body]
  (list 'if (list 'not test) body))

(macroexpand '(unless condition body))

(unless true (println "No more danger, Will."))

(unless false (println "Now,THIS is The FORCE"))

; 7.3.7 第二天自習

; 找
; -------------
; q1. Clojure 中一些常用宏的實現。
; q2. 一個自定義延遲序列的例子。
; q3. defrecord 和 protocol 目前的狀態。(在編寫本書時，這些特性還在開發中。)

; 做
; -------------
; q1. 用宏實現一個飽含else條件的unless。
; q2. 編寫一個類型用 defrecord實現一個協議。



