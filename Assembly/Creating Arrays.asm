# Creating Arrays
# int index, A(10);
# index = A(index);

# Allocates space in memory for A and index and index and use the appropriate
# pseudo instructions to load their respective base addresses into registers
# $t0 and $t1. Initialize index with 8.

.data
index: .word 8
A: .word 0, 1, 2, 3, 4, 5, 6, 7, 8, 9

.text
main:
	la $t0, A
	la $t1, index
	lw $t2, 0($t1) 		# load the value of index into $t2
	sll $t2, $t2, 2 	# multiply the index by 4 to get the correct offset for the A array
	add $t3, $t0, $t2 	# add the offset to the base address of A
	lw $t4, 0($t3) 		# load the value of A[index] into $t4
	sw $t4, 0($t1) 		# store the value of A[index] into index


# ===================={ Shows above code works }==================== #
	# Print the value of A[0] (which is 0)
	lw $a0, 0($t0) 		# load the value of A[0] into $a0
	li $v0, 1 			# [System]: Print Integer
	syscall

	# Print the value of A[8] (which is 7)
	lw $a0, 28($t0) 	# load the value of A[8] into $a0 (32 is the byte offset for the 8th index)
	li $v0, 1 			# [System]: Print Integer
	syscall
	
	# Print the value of A[9] (which is 8)
	lw $a0, 32($t0) 	# load the value of A[8] into $a0 (32 is the byte offset for the 8th index)
	li $v0, 1 			# [System]: Print Integer
	syscall
	
	# Print the value of A[10] (which is 9)
	lw $a0, 36($t0) 	# load the value of A[8] into $a0 (32 is the byte offset for the 8th index)
	li $v0, 1 			# [System]: Print Integer
	syscall
	
	# Print the value of A[11] (which is 0)
	lw $a0, 40($t0) 	# load the value of A[8] into $a0 (32 is the byte offset for the 8th index)
	li $v0, 1 			# [System]: Print Integer
	syscall

	# Exit
	li $v0, 10 			# [System]: Exit
	syscall
	
	
# ============================{ Output }============================ #
# 07890
