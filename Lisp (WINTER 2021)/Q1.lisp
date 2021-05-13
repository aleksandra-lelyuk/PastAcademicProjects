;NOTE: for the sake of the assignments, no utility functions were to be used.

;(1)-------------------------------------------------------
;function that calculates distance between two points in 2d space
;anonymous function test1
(print ((lambda (P1 P2) (sqrt (+ (* (- (car P1) (car P2)) (- (car P1) (car P2))) (* (- (car (cdr P1)) (car (cdr P2))) (- (car (cdr P1)) (car (cdr P2))))))) '(4 2) '(4 2)))

;anonymous function test2
(print ((lambda (P1 P2) (sqrt (+ (* (- (car P1) (car P2)) (- (car P1) (car P2))) (* (- (car (cdr P1)) (car (cdr P2))) (- (car (cdr P1)) (car (cdr P2))))))) '(-7 -4) '(17 6.5)))


;non anonymous function 
(defun distance(P1 P2)
    (print(sqrt (+ (* (- (car P1) (car P2)) (- (car P1) (car P2))) (* (- (car (cdr P1)) (car (cdr P2))) (- (car (cdr P1)) (car (cdr P2)))))))
)

;non anonymous function test1
(distance '(4 2) '(4 2))
;non anonymous function test2
(distance '(-7 -4) '(17 6.5))



;(2)-------------------------------------------------------
;function that accepts a list of alphanumerical characters and removes the numbers as well as duplicate letters. 
;returns a list of the letter in the original list with no duplicates
(defun clean(lst)
    (cond ((null lst) '())
        ((listp (car lst))(clean(append (clean (car lst)) (cdr lst))))
        ((numberp (car lst))(clean(cdr lst)))
        ((member (car lst) (clean(cdr lst)))(clean(cdr lst)))
        (t (cons (car lst)(clean (cdr lst))))
    )
)

(print (clean '((z f) (b a 5 3.5) 6 (7) (a) c)))
(print (clean '((n) 2 (6 h 7.8) (w f) (n) (c) n)))


;(3)-------------------------------------------------------
;program that swaps first and last element
(defun my_reverse(lst)
    (cond ((null lst) '())
        (t (append (my_reverse (cdr lst)) (list (car lst))))))
 
(defun first_last_swap(lst)
    (let* ((f (car lst))
        (newlst (my_reverse (cdr lst))))
    (append (list (car newlst)) (my_reverse (cdr newlst)) (list f))))


(print (first_last_swap '((a d) f 10 w h)))
(print (first_last_swap '(g 6 p 10 m)))


;(4)-------------------------------------------------------
;checks whether a provided list is a valid representation of a binary search tree

(defun isabst(lst)
    (cond ((null lst) t)
        ((and (not (null (car(car (cdr lst))))) (> (car(car (cdr lst))) (car lst))) nil)
        ((and (not (null (car(car (cdr (cdr lst)))))) (< (car(car (cdr (cdr lst)))) (car lst))) nil)
        
        (t (and (isabst (car (cdr lst))) (isabst (car (cdr (cdr lst))))))))

;testing a BST
(print (isabst '(8 (3 (1 () ()) (6 (4 () ())( 7 () ()))) (10 (()) (14 (13) ())))))

;testing a non BST
(print (isabst '(8 (11 (1 () ()) (6 (4 () ())( 7 () ()))) (10 (()) (14 (13) ())))))



;(5)-------------------------------------------------------
;program to compute series for sin(x) if -10<x<10, n is odd; cos(x) if n is even
(defun power(base exp)
    (if (eq exp 0)
        1
        (* base (power base (- exp 1)))))


(defun factorial (num)
    (if (= num 1)
        1
        (* num (factorial (- num 1)))))


(defun my_sin (x n)
    (setq i 1)
    (setq ans 0)
    (loop 
     (setq ans (+ ans (/ (power x i) (factorial i))))
     (setq i (+ i 2))
     (if (> i n)
         (return ans)
         (setq ans (- ans (/ (power x i) (factorial i)))))
     (setq i (+ i 2))
     (when (> i n) (return ans))))
   

(defun my_cos (x n)
    (setq i 2)
    (setq ans 1)
    (loop 
     (setq ans (- ans (/ (power x i) (factorial i))))
     (setq i (+ i 2))
     (if (> i n)
         (return ans)
         (setq ans (+ ans (/ (power x i) (factorial i)))))
     (setq i (+ i 2))
     (when (> i n) (return ans))))
   

(defun sin-cos-comp (x n)
    (cond 
        ((typep n 'string)(print "n is a string") NIL)
        ((not (typep n 'integer))(print "n is not an integer") NIL)
        ((and (oddp n) (and (< -10 x) (> 10 x))) (my_sin x n))
        ((evenp n) (my_cos x n))
        (t (print "error: value of n is of unacceptable type") NIL)))


(print "TEST CASES")
(terpri)

(print "1. x = 2, n = 'i'm a string'")
(print (sin-cos-comp 2 "i'm a string"))
(terpri)

(print "2. x = 2, n = 4.5")
(print (sin-cos-comp 2 4.5))
(terpri)

(print "3. x = 2, n = 5 (n is odd and -10 < x < 10)")
(print (sin-cos-comp 2 5))
(terpri)

(print "4. x = 11, n = 5 (n is odd and not(-10 < x < 10))")
(print (sin-cos-comp 11 5))
(terpri)

(print "5. x = 2, n = 8 (n is even)")
(print (sin-cos-comp 2 8))

;(6)-------------------------------------------------------
;program to compute Pell numbers using (a) iterative approach and (b) recursive approach
; recursive approach

(defun lastelem(lst)
    (cond ((null lst) '())
        ((null (cdr lst)) (car lst))
        (t (lastelem (cdr lst)))))

(defun pellrec(n)
    (cond ((= n 0) '(0))
        ((= n 1) (append (pellrec 0) '(1)))
        (t (append (pellrec(- n 1)) (cons (+ (* 2 (lastelem (pellrec (- n 1)))) (lastelem (pellrec (- n 2)))) '())))))

(print "Recursive function for Pell numbers sequence:")
(terpri)
(print "Sequence for first 0 Pell numbers")
(print (pellrec 0))
(terpri)
(print "Sequence for first 1 Pell numbers")
(print (pellrec 1))
(terpri)
(print "Sequence for first 6 Pell numbers")
(print (pellrec 6))
(terpri)
(print "Sequence for first 11 Pell numbers")
(print (pellrec 11))
(terpri)
(terpri)
(terpri)

; iterative approach

(defun pellit(n)
    (setq a 0)
    (setq b 1)
    (setq temp 0)
    (setq i 1)
    (setq lst '(0 1))
    (cond ((= n 0) '(0))
        ((= n 1) lst)
        (t (loop
               (setq temp b)
               (setq b (+ (* 2 b) a))
               (setq a temp)
               (setq lst (append lst (cons b '())))
               (setq i (+ i 1))
               (when (= i n) (return lst))))))

(print "Iterative function for Pell numbers sequence:")
(terpri)
(print "Sequence for first 0 Pell numbers")
(print (pellit 0))
(terpri)
(print "Sequence for first 0 Pell numbers")
(print (pellit 1))
(terpri)
(print "Sequence for first 0 Pell numbers")
(print (pellit 6))
(terpri)
(print "Sequence for first 0 Pell numbers")
(print (pellit 11))
