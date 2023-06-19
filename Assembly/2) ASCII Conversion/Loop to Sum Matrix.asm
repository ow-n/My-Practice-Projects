# Demonstrates summing everything in a matrix using a loop

.data
M: .word 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4,
sum: .word 0
arrow: .asciiz	" -> "

.text
.globl main

main:
	jal get_sum
	jal exit_program

get_sum:
	move $s0, $ra			# save the return address in $s0
	la $t0, M          		# load the address of the array into $t0
	li $v0, 4               # syscall code for printing an word
	move $a0, $t0           # move the matrix to $a0
	syscall                 # print the matrix
	jal reset_i_j
	jal calculate_sum
	
	sw $t3, sum            	# store the sum in the 'sum' label
	li $v0, 1               # syscall code for printing an integer
	move $a0, $t3           # move the sum to $a0
	syscall                 # print the sum
	move $ra, $s0			# save the return address in $s0
	jr $ra

		calculate_sum:
			li $t1, 16           	# load the size of the array into $t1
    		beq $s1, $t1, exit_loop # if the loop counter is equal to the size, exit the loop
    		lw $t4, 0($t0)          # load the current array element into $t4
   			add $t3, $t3, $t4       # add the current element to the sum
   			addi $t0, $t0, 4        # increment the array address by 4 bytes (since it's a word array)
   			addi $s1, $s1, 1        # increment the loop counter
    
   		 	# Prints every calculation
    		li $v0, 1
    		la $a0, ($t3)
    		syscall
    		li $v0, 4
   		 	la $a0, arrow
    		syscall
    
    		j calculate_sum			# jump back to the start of the loop

				exit_loop:
   					jr $ra
   		
		reset_i_j:
			addi $s1, $zero, 0		# reset i to 0 ($s1)
			addi $s2, $zero, 0		# reset j to 0 ($s2)
			sw $s1, 0($sp)			# store i on stack (index of input string(source))
			sw $s2, 4($sp)			# store j on stack (index of integer array(target))
			jr $ra					# Return
   		
		exit_program:
			li $v0, 10              # syscall code for exit
    		syscall                 # exit