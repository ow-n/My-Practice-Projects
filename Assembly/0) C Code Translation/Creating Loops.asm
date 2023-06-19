# Creating Loops
# int M(4), N(4);
# for (i=0; i < 3; i++) {
#    M(i+1) =  N(i);  }

# First, allocate space in memory for M and N and use the appropriate pseudo
# instruction to load the base addresses of the two arrays into registers
# t0 and t1 respectively. M should hold 1,2,3,4 and N; 5,6,7,8.

.data
M: .word 1, 2, 3, 4
N: .word 5, 6, 7, 8
space: .asciiz " "

.text
main:
	la $t0, M
	la $t1, N
	li $t2, 0		#index
	li $t3, 3		#bound

loop:
	lw $t4, 0($t1)
	sw $t4, 4($t0)
	addi $t0, $t0, 4
	addi $t1, $t1, 4
	addi $t2, $t2, 1
	bne $t2, $t3, loop


# ===================={ Shows above code works }==================== #
    # Reset the base address of M
    la $t0, M

    # Print the contents of the M array
    li $t5, 4		# array size
    li $t6, 0		# loop counter for printing
    
print_loop:
    lw $t7, 0($t0)
    li $v0, 1		# [System]: Print Integer
    move $a0, $t7
    syscall

    # Print a space
    li $v0, 4		# [System]: Print String
    la $a0, space
    syscall

    addi $t0, $t0, 4
    addi $t6, $t6, 1
    blt $t6, $t5, print_loop

    # Exit
    li $v0, 10		# [System]: Exit
    syscall


# ============================{ Output }============================ #
# 1567
