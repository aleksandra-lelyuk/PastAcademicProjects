(defun isabst(lst)
    (cond ((null lst) t)
        ((and (not (null (car(car (cdr lst))))) (> (car(car (cdr lst))) (car lst))) nil)
        ((and (not (null (car(car (cdr (cdr lst)))))) (< (car(car (cdr (cdr lst)))) (car lst))) nil)
        
        (t (and (isabst (car (cdr lst))) (isabst (car (cdr (cdr lst))))))))

;testing a BST
(print (isabst '(8 (3 (1 () ()) (6 (4 () ())( 7 () ()))) (10 (()) (14 (13) ())))))

;testing a non BST
(print (isabst '(8 (11 (1 () ()) (6 (4 () ())( 7 () ()))) (10 (()) (14 (13) ())))))