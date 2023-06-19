# Caches & Associativity - 8x8 Matrix Multiply

# All tests are done with: LRU - 8 Blocks - 16 Block Size - 512 Cache Size
# Matrix Multiply = 87% Cache Hit Rate, 2 Way Set Associative = 91% Cache Hit Rate
# Blocked Matrix Multiply = 89% Cache Hit Rate, 2 Way Set Associative = 85% Cache Hit Rate
# The Block Matrix Multiply had a better hit rate directly mapped.
# The Matrix multiply had a better hit rate as a 2 way set associative.

.globl main

.data
M1:		  		.word 1, 1, 1, 1, 1, 1, 1, 1
          		.word 1, 1, 1, 1, 1, 1, 1, 1
          		.word 1, 1, 1, 1, 1, 1, 1, 1
          		.word 1, 1, 1, 1, 1, 1, 1, 1
          		.word 1, 1, 1, 1, 1, 1, 1, 1
          		.word 1, 1, 1, 1, 1, 1, 1, 1
          		.word 1, 1, 1, 1, 1, 1, 1, 1
          		.word 1, 1, 1, 1, 1, 1, 1, 1
M2:		  		.word 1, 1, 1, 1, 1, 1, 1, 1
          		.word 2, 2, 2, 2, 2, 2, 2, 2
          		.word 3, 3, 3, 3, 3, 3, 3, 3
          		.word 4, 4, 4, 4, 4, 4, 4, 4
          		.word 5, 5, 5, 5, 5, 5, 5, 5
          		.word 6, 6, 6, 6, 6, 6, 6, 6
          		.word 7, 7, 7, 7, 7, 7, 7, 7
          		.word 8, 8, 8, 8, 8, 8, 8, 8
				.align 4		# ensures V is aligned to a word boundary
MM:				.space 256		# 4x4 Matrix with 256 bytes of memory

space: 			.asciiz 		" "
newline: 		.asciiz 		"\n"
ask_row:		.asciiz			"Row "
colon:			.asciiz			": "
M1_label:		.asciiz			"Printing Matrix 1..."
M2_label:		.asciiz			"Printing Matrix 2..."
MM_process:		.asciiz			"Multiplying M1 to M2..."
MMB_process:	.asciiz			"Block Multiplying M1 to M2..."
loop_j_fin:		.asciiz			"Loop j | "
loop_i_fin:		.asciiz			"Loop i | "
MM_label:		.asciiz			"Printing Matrix Multiplied..."

.text
main:
	# Make First Matrix
	jal print_M1
	jal print_M2
	
	jal multiply_matrix
	jal print_MM

	jal multiply_matrix_blocked
	jal print_MM
	
	jal exit_program

# ============================{ Subroutines }============================ #
print_M1:
	addiu $sp, $sp, -4  		# Allocate space on the stack
	sw $ra, 0($sp)      		# Store the return address to the stack
    la $t0, M1		     		# Load address of matrix1 into $t0
    li $t1, 8           		# Set row size / loop termination value
    li $t2, 0           		# Initialize row counter

	li $v0 4					# Print "Printing M1..."
	la $a0 M1_label
	syscall
	jal new_line

    # Outer loop: iterate over each row
    M1_outer_loop:
        li $t3, 0       			# Reset column counter

        # Inner loop: iterate over each column in the current row
        M1_inner_loop:
            sll $t4, $t3, 2      		# Calculate offset (index * 4)
            add $t4, $t4, $t0    		# Add base address to get address of current element
            lw $t5, 0($t4)       		# Load current element
            li $v0, 1            		# System call number for print integer
            move $a0, $t5        		# Move current element to argument register
            syscall              		# System call to print integer
            jal print_space
            addiu $t3, $t3, 1    		# Increment column counter
			blt $t3, $t1, M1_inner_loop 	# If column counter < 8, continue inner loop

        jal new_line
        addiu $t0, $t0, 32   		# Move to next row (8 words * 4 bytes)
        addiu $t2, $t2, 1    		# Increment row counter
        blt $t2, $t1, M1_outer_loop 	# If row counter < 8, continue outer loop

	jal new_line
	lw $ra, 0($sp)      		# Load the return address from the stack
	addiu $sp, $sp, 4   		# Deallocate the stack space
	jr $ra 						# Return from subroutine

# ======================================================================= #

print_M2:
	addiu $sp, $sp, -4  		# Allocate space on the stack
	sw $ra, 0($sp)      		# Store the return address to the stack
    la $t0, M2		     		# Load address of matrix1 into $t0
    li $t1, 8           		# Set row size / loop termination value
    li $t2, 0           		# Initialize row counter

	li $v0 4					# Print "Printing M1..."
	la $a0 M2_label
	syscall
	jal new_line

    # Outer loop: iterate over each row
    M2_outer_loop:
        li $t3, 0       			# Reset column counter

        # Inner loop: iterate over each column in the current row
        M2_inner_loop:
            sll $t4, $t3, 2      		# Calculate offset (index * 4)
            add $t4, $t4, $t0    		# Add base address to get address of current element
            lw $t5, 0($t4)       		# Load current element
            li $v0, 1            		# System call number for print integer
            move $a0, $t5        		# Move current element to argument register
            syscall              		# System call to print integer
            jal print_space
            addiu $t3, $t3, 1    		# Increment column counter
			blt $t3, $t1, M2_inner_loop 	# If column counter < 8, continue inner loop

        jal new_line
        addiu $t0, $t0, 32   		# Move to next row (8 words * 4 bytes)
        addiu $t2, $t2, 1    		# Increment row counter
        blt $t2, $t1, M2_outer_loop 	# If row counter < 8, continue outer loop

	jal new_line
	lw $ra, 0($sp)      		# Load the return address from the stack
	addiu $sp, $sp, 4   		# Deallocate the stack space
	jr $ra 						# Return from subroutine

# ======================================================================= #

print_MM:
	addiu $sp, $sp, -4  		# Allocate space on the stack
	sw $ra, 0($sp)      		# Store the return address to the stack
    la $t0, MM		     		# Load address of matrix1 into $t0
    li $t1, 8           		# Set row size / loop termination value
    li $t2, 0           		# Initialize row counter

	li $v0 4					# Print "Printing M1..."
	la $a0 MM_label
	syscall
	jal new_line

    # Outer loop: iterate over each row
    MM_outer_loop:
        li $t3, 0       			# Reset column counter

        # Inner loop: iterate over each column in the current row
        MM_inner_loop:
            sll $t4, $t3, 2      		# Calculate offset (index * 4)
            add $t4, $t4, $t0    		# Add base address to get address of current element
            lw $t5, 0($t4)       		# Load current element
            li $v0, 1            		# System call number for print integer
            move $a0, $t5        		# Move current element to argument register
            syscall              		# System call to print integer
            jal print_space
            addiu $t3, $t3, 1    		# Increment column counter
			blt $t3, $t1, MM_inner_loop 	# If column counter < 8, continue inner loop

        jal new_line
        addiu $t0, $t0, 32   		# Move to next row (8 words * 4 bytes)
        addiu $t2, $t2, 1    		# Increment row counter
        blt $t2, $t1, MM_outer_loop 	# If row counter < 8, continue outer loop

	jal new_line
	lw $ra, 0($sp)      		# Load the return address from the stack
	addiu $sp, $sp, 4   		# Deallocate the stack space
	jr $ra 						# Return from subroutine

# ======================================================================= #

multiply_matrix:
	addiu $sp, $sp, -4  	# allocate space on the stack
	sw $ra, 0($sp)      	# store the return address to the stack
	li $v0, 4
	la $a0, MM_process
	syscall
	jal new_line
	la $s5, M1 				# load the base address of matrix matrix1* into $s5
	la $s6, M2 				# load the base address of matrix matrix2* into $s6
	la $s7, MM 				# load the base address of matrix MM into $s7

	# Save Loop termination value (8) + Initialize loop variables
	li $t1, 8 				# $t1 = 8 (row size/loop end)
	li $s1, 0 				# i = 0; initialize 1st for loop
	
	MM_L1: li $s2, 0 				# j = 0; restart 2nd for loop
	MM_L2: li $s3, 0 				# k = 0; restart 3rd for loop

		# First Step: skip over the i “1D Arrays” / Rows, to get the one we want.
		# So: multiply the index in the first dimension by the size of the row, 4.
		# Since 4 is a power of 2, we can use a shift instead:
		sll $t2, $s1, 3 		# $t2 = i * 2^3 (size of row of c)

		# Now we add the second index to select the jth element of the desired row:
		addu $t2, $t2, $s2 		# $t2 = i * size(row) + j

		# To turn this sum into a byte index, we multiply it by the size of a matrix element
		# in bytes. Since each element is 4 byte, we can instead shift left by 02
		sll $t2, $t2, 2 		# $t2 = byte offset of [i][j]

		# Next we add this sum to the base address of c, giving the address of c[i][j],
		# and then load the number c[i][j] into $t3:
		addu $t2, $s7, $t2 		# $t2 = byte address of c[i][j]
		lw $t3, 0($t2) 			# $t3 = 4 bytes of c[i][j]

		# The following five instructions are virtually identical to the last five:
		# Calculate the address and then load the number b[k][j].
		MM_L3: sll $t0, $s3, 3 		# $t0 = k * 2^2 (size of row of b)
			addu $t0, $t0, $s2		# $t0 = k * size(row) + j
			sll $t0, $t0, 2 		# $t0 = byte offset of [k][j]
			addu $t0, $s6, $t0 		# $t0 = byte address of b[k][j]
			lw $t4, 0($t0) 			# $t4 = 4 bytes of b[k][j]

			# Similarly, the next five instructions are like the last five:
			# Calculate the address and then load the number a[i][k].
			sll $t0, $s1, 3 		# $t0 = i * 8 (size of row of a)
			addu $t0, $t0, $s3 		# $t0 = i * size(row) + k
			sll $t0, $t0, 2 		# $t0 = byte offset of [i][k]
			addu $t0, $t0, $s5		# $t0 = byte address of a[i][k]
			lw $t5, 0($t0) 			# $t5 = 4 bytes of a[i][k]

			# Now that we have loaded all the data, we are finally ready to do some
			# operations! We multiply elements of a and b located in registers $t5 and $t4,
			# and then accumulate the sum in $t3.
			mul $t4, $t5, $t4 		# $t4 = a[i][k] * b[k][j]
			add $t3, $t3, $t4 		# $t3 = c[i][j] + a[i][k] * b[k][j]

			# The final block increments the index k and loops back if the5 5 5 5 index is not 4.
			# If it is 4, and thus the end of the innermost loop, we need to store the sum
			# accumulated in $t3 into c[i][j].
			addiu $s3, $s3, 1 		# $k = k + 1
			bne $s3, $t1, MM_L3 		# if (k != 32) go to L3
			sw $t3, 0($t2) 			# c[i][j] = $t3

			# Similarly, these final four instructions increment the index variable of the
			# middle and outermost loops, looping back if the index is not 4 and exiting if
			# the index is 4.
			addiu $s2, $s2, 1 		# $j = j + 1
					# Debugging - Prints everytime j loop
					li $v0, 4
					la $a0, loop_j_fin
					syscall 
			bne $s2, $t1, MM_L2 	# if (j != 4) go to L2
			addiu $s1, $s1, 1 		# $i = i + 1
					# Debugging - Prints everytime i loop
					li $v0, 4
					la $a0, loop_i_fin
					syscall
					jal new_line 
			bne $s1, $t1, MM_L1 	# if (i != 8) go to L1
			
			jal new_line
 			add $s0, $s0, 1			# so that print_matrix know to print the 3rd matrix
			lw $ra, 0($sp)      	# load the return address from the stack
			addiu $sp, $sp, 4   	# deallocate the stack space
			jr $ra

# ======================================================================= #

multiply_matrix_blocked:
	addiu $sp, $sp, -4  	# allocate space on the stack
	sw $ra, 0($sp)       	# store the return address to the stack
	li $v0, 4
	la $a0, MMB_process
	syscall
	jal new_line
	la $s5, M1 				# load the base address of matrix M1 into $s5
	la $s6, M2 				# load the base address of matrix M2 into $s6
	la $s7, MM 				# load the base address of matrix MM into $s7

	li $t1, 8 				# Loop end = 8 as we are dealing with 8x8 matrices
	li $s1, 0 				# i = 0; initialize 1st for loop
	
	MMB_L1: li $s2, 0 			# j = 0; restart 2nd for loop
	MMB_L2: li $s3, 0 			# k = 0; restart 3rd for loop
		li $t3, 0 				# reset $t3 to 0 for each output element

	# Starting of the 1st for loop (i loop)
	MMB_L3: sll $t2, $s2, 3 	# $t2 = j * 8 (size of block of c)
		addu $t2, $t2, $s1 		# $t2 = j * size(block) + i
		sll $t2, $t2, 2 		# $t2 = byte offset of [i][j]
		addu $t2, $s7, $t2 		# $t2 = byte address of c[i][j]

		sll $t0, $s3, 3 		# $t0 = k * 8 (size of block of a)
		addu $t0, $t0, $s1 		# $t0 = k * size(block) + i
		sll $t0, $t0, 2 		# $t0 = byte offset of [i][k]
		addu $t0, $s5, $t0		# $t0 = byte address of a[i][k]
		lw $t5, 0($t0) 			# $t5 = 4 bytes of a[i][k]

		sll $t0, $s3, 3 		# $t0 = k * 8 (size of block of b)
		addu $t0, $t0, $s2		# $t0 = k * size(block) + j
		sll $t0, $t0, 2 		# $t0 = byte offset of [k][j]
		addu $t0, $s6, $t0 		# $t0 = byte address of b[k][j]
		lw $t4, 0($t0) 			# $t4 = 4 bytes of b[k][j]

		mul $t4, $t5, $t4      	# $t4 = a[i][k] * b[k][j]
		add $t3, $t3, $t4      	# $t3 = c[i][j] + a[i][k] * b[k][j]

		addiu $s3, $s3, 1      	# $k = k + 1
		bne $s3, $t1, MMB_L3   	# if (k != 8) go to L3
		
		sw $t3, 0($t2)         	# c[i][j] = $t3; save the result only after the inner loop

		addiu $s2, $s2, 1 		# $j = j + 1
		bne $s2, $t1, MMB_L2	# if (j != 8) go to L2
		addiu $s1, $s1, 1 		# $i = i + 1
		bne $s1, $t1, MMB_L1	# if (i != 8) go to L1
	
	jal new_line
	add $s0, $s0, 1			# so that print_matrix know to print the 3rd matrix
	lw $ra, 0($sp)      	# load the return address from the stack
	addiu $sp, $sp, 4   	# deallocate the stack space
	jr $ra

# ======================================================================= #

exit_program:
	li $v0, 10				# [System]: Exit
	syscall

	print_space:
		li $v0, 11				# [Service]: Print Character
		li $a0, 32				# load ASCII value for space into $a0
		syscall
		jr $ra

	new_line:
		li $v0, 11				# [System]: Print Character
		li $a0, 10				# load ASCII code for newline character
		syscall
		jr $ra					# return from subroutine (using $ra)

	exit_loop:
		jr $ra					# Return from subroutine (using $ra)




# =========================={ Notes }========================== #
# $s0 = matrix counter (0 -> 1 -> 2 -> ...)
# $s1 = i (rows), $s2 = j (columns) $s3 = k
# $s5 = M1, $s6 = M2, $s7 = MM


# ======================={ Output One }======================= #
# Printing Matrix 1...
# 1 1 1 1 1 1 1 1 
# 1 1 1 1 1 1 1 1 
# 1 1 1 1 1 1 1 1 
# 1 1 1 1 1 1 1 1 
# 1 1 1 1 1 1 1 1 
# 1 1 1 1 1 1 1 1 
# 1 1 1 1 1 1 1 1 
# 1 1 1 1 1 1 1 1 
# 
# Printing Matrix 2...
# 1 1 1 1 1 1 1 1 
# 2 2 2 2 2 2 2 2 
# 3 3 3 3 3 3 3 3 
# 4 4 4 4 4 4 4 4 
# 5 5 5 5 5 5 5 5 
# 6 6 6 6 6 6 6 6 
# 7 7 7 7 7 7 7 7 
# 8 8 8 8 8 8 8 8 
# 
# Multiplying M1 to M2...
# Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop i | 
# Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop i | 
# Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop i | 
# Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop i | 
# Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop i | 
# Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop i | 
# Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop i | 
# Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop j | Loop i | 
# 
# Printing Matrix Multipled...
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 
# Block Multiplying M1 to M2...
# 
# Printing Matrix Multiplied...
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
# 36 36 36 36 36 36 36 36 
