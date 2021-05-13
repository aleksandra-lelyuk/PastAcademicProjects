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

