
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
               
        