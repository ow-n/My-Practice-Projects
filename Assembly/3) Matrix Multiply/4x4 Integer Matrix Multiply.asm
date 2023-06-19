# 4x4 Integer Matrix Multiply

.globl main

.data
Row:			.space 8 		# 4integers, + 3spaces + 1null = 8
ask_matrix:		.asciiz 		"Input 4x4 Matrix "
ask_row:		.asciiz			"Row "
colon:			.asciiz			": "
				.align 4		# ensures V is aligned to a word boundary
M1_label:		.asciiz			"Printing M1 Matrix..."
M2_label:		.asciiz			"Printing M2 Matrix..."
MM_process:		.asciiz			"Multiplying M1 to M2..."
MM_label:		.asciiz			"Printing MM Matrix..."
				.align 4		# ensures V is aligned to a word boundary
M1:				.space 64		# 4x4 Matrix with 64 bytes of memory
				.align 4		# ensures V is aligned to a word boundary
M2:				.space 64		# 4x4 Matrix with 64 bytes of memory
				.align 4		# ensures V is aligned to a word boundary
MM:				.space 64		# 4x4 Matrix with 64 bytes of memory
# Variables for Debugging
arrow:			.asciiz			" -> "
print_sum:		.asciiz			"Sum: "
				.align 4		# ensures V is aligned to a word boundary
sum:			.space 4		# Sum of Matrix
parity:			.asciiz			"Parity: "
inside_M1: 		.asciiz			"(Inside make_matrix 1) "
inside_M2: 		.asciiz			"(Inside make_matrix 2) "
MTest:			.asciiz			"1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4"
loop_j_fin:		.asciiz			"Loop j finished "
loop_i_fin:		.asciiz			"Loop i finished "
 
.text
main:
	# Make First Matrix
	jal make_matrix_M			# Make 4x4 Matrix M1
	jal get_sum					# Prints Sum
	jal get_parity				# Prints Even or Odd
	jal print_M					# Prints M1
	
	# Make Second Matrix
	jal make_matrix_M			# Make 4x4 Matrix M2
	jal get_sum					# Prints Sum
	jal get_parity				# Prints Even or Odd
	jal print_M					# Prints M2
	
	# Multiply Matrix
	jal multiply_matrix			# Multiplies M1 and M2
	jal print_M					# Prints MM
	
	jal exit_program

# ============================{ Subroutines }============================ #
make_matrix_M:
    li $t0, 4               	# store constant '4' for loop termination
    beq $s3, $t0, exit_make_matrix	# if k == 4, then exit
	beqz $t7, get_row			# if $t7 == 0, Print "Input 4x4 Matrix #"
    jal new_line
    jal print_row_number    	# Print "Row #: "
    # Read input code, format:
    li $v0, 8               	# [Service]: Read String
    la $a0, Row         		# load address of input buffer into $a0 (input buffer = temp storage)
    li $a1, 8           		# set maximum number of characters to be read
    syscall
    li $s1,0                	# reset $s1 (i / col) to 0
    jal print_arrow				# Print " -> "
    sll $t4, $s3, 4				# calculate row offset, $s5 = $s3 * 16, 16 = 2^4
    li $t0, 8              		# store constant '8' for loop termination
    jal string_to_integer		# Convert Row into integer + Stored in Matrix
    jal reset_i_j				# Resets index of string and integer
    # Store updated array index
    addi $s3, $s3, 1			# loop index k + 1
    j make_matrix_M				# Loop: jump to string_to_integer

	string_to_integer:
   		beq $s1, $t0, exit_loop 	# if i == 8, then exit
   		la $t1, Row         		# load address of input buffer into $t1 (string_array index)
   		# Get position of string_array
   		add $t1, $t1, $s1        	# increment string_array by i(position of string array)
   		lb $t2, 0($t1)          	# load first byte from string_array
   		# Convert ASCII to Integer (Masking Technique) (I did arithmetic last lab)
   		andi $t2, $t2, 0x0F			# mask first digit with 0x0F to get the integer value
		li $v0, 1					# [Service]: Print Integer in $a0
		move $a0, $t2				# copy $t8 integer to $a0
		syscall
		li $v0, 11					# [Service]: Print Character
		li $a0, 32					# load ASCII value for space into $a0
		syscall
		# Store integer in 
		li $t3, 1
		beq $s0, $t3, make_M1		# branch if $s0(matrix counter) == 1
		li $t3, 2
		beq $s0, $t3, make_M2		# branch if $s0(matrix counter) == 2

		make_M1:
			la $s5, M1                	# load address of integer matrix M1 into $s5
			add $s5, $s5, $t4        	# add row offset (in $s5) to the address of M1
			add $s5, $s5, $s2        	# add column number to offset to get next byte
			sw $t2, 0($s5)           	# store new integer($a0) into M1($t3)
			# Store updated array index
			addi $s1, $s1, 2       		# i (input index) + 2 (2 digits + ' ')
			addi $s2, $s2, 4			# j (int array) + 4 (next byte)
			j string_to_integer			# Loop: jump to string_to_integer

		make_M2:
			la $s6, M2                	# load address of integer matrix M2 into $s6
			add $s6, $s6, $t4        	# add row offset (in $s6) to the address of M2
			add $s6, $s6, $s2        	# add column number to offset to get next byte
			sw $t2, 0($s6)           	# store new integer($a0) into M2($s6)
			# Store updated array index
			addi $s1, $s1, 2       		# i (input index) + 2 (2 digits + ' ')
			addi $s2, $s2, 4			# j (int array) + 4 (next byte)
			j string_to_integer			# Loop: jump to string_to_integer

	get_row:
   	 	# Instructions that execute once in make_matrix loop
   	 	addiu $sp, $sp, -4  		# allocate space on the stack
		sw $ra, 0($sp)      		# store the return address to the stack
   	 	add $s0, $s0, 1				# to print next matrix "0 -> 1 -> 2"
		add $t7, $t7, 1				# stops "Input Matrix" from looping by $t7 + 1
		li $s2, 0					# resets column counter (j / $s2) to 0
		# Prints strings
		li $v0, 4					# [Service]: Print String
		la $a0, ask_matrix			# load address of ask_matrix
		syscall
		li $v0, 1					# [Service]: Print Integer
		la $a0, 0($s0)				# load address of matrix number
		syscall
		j make_matrix_M				# Returns to make_matrix_M

	print_row_number: 				# Print "Row #: "
		li $v0, 4					# [Service]: Print String
		la $a0, ask_row				# load address of ask_Row
		syscall
		li $v0, 1					# [Service]: Print Integer
		addi $a0, $s3, 1
		syscall
		li $v0, 4					# [Service]: Print String
		la $a0, colon				# load address of colon
		syscall
		jr $ra

	reset_i_j:
		li $s1, 0					# reset i to 0 ($s1)
		li $s2, 0					# reset j to 0 ($s2)
		jr $ra						# Return

	print_arrow:
		li $v0, 4					# [Service]: Print String
		la $a0, arrow				# load address of arrow
		syscall
		jr $ra

	exit_make_matrix:
		li $t7, 0					# resets condition for get_Row to branch
		li $s3, 0					# resets condition for exit_make_matrix to branch
		jal new_line
		lw $ra, 0($sp)      		# load the return address from the stack
		addiu $sp, $sp, 4   		# deallocate the stack space
		jr $ra						# Return from subroutine (using $ra)

# ======================================================================= #

# $t0 = M
# $t1 = termination value
# $t2 = address of sum
# $t3 = temp storage for sum
# $t4 = branch condition
get_sum:
	addiu $sp, $sp, -4  		# allocate space on the stack
	sw $ra, 0($sp)      		# store the return address to the stack
	li $v0, 4					# [Service]: Print String
	la $a0, print_sum			# load "Sum: "
	syscall
	li $s1, 0					# reset i to 0 ($s1)
	li $t3, 0          			# initialize sum to 0
	li $t1, 16					# termination value = size of matrix, 4x4=16
	la $t2, sum					# load address of sum into $s0
	# if (matrix counter($s0) == 1 or 2) { 
	li $t4, 1
	beq $s0, $t4, load_M1		# branch if $s0(matrix counter) == 1
	li $t4, 2
	beq $s0, $t4, load_M2		# branch if $s0(matrix counter) == 2
	# } else {
	lw $ra, 0($sp)      		# load the return address from the stack
	addiu $sp, $sp, 4  	 		# deallocate the stack space
	jr $ra

	load_M1:
		la $t0, M1          		# load the address of the array into $t0
				# Debugging - Print "Inside M1"
				# li $v0, 4
				# la $a0, inside_M1
				# syscall
		jal calculate_sum			# calculate sum into $t3
		sw $t3, 0($t2)				# store sum into sum
		
		li $v0, 1              		# [Service]: Print Integer
		lw $a0, sum        	  		# load sum
		syscall
		jal new_line
		lw $ra, 0($sp)      		# load the return address from the stack
		addiu $sp, $sp, 4   		# deallocate the stack space
		jr $ra
	
	load_M2:
		la $t0, M2          		# load the address of the array into $t0
				# Debugging - Print "Inside M1"
				# li $v0, 4
				# la $a0, inside_M2
				# syscall	
		jal calculate_sum			# calculate sum into $t3
		sw $t3, 0($t2)				# store sum into sum
		
		li $v0, 1              		# [Service]: Print Integer
		move $a0, $t3        	  	# move the sum to $a0
		syscall
		jal new_line
		lw $ra, 0($sp)      		# load the return address from the stack
		addiu $sp, $sp, 4   		# deallocate the stack space
		jr $ra
		
		calculate_sum:
    		beq $s1, $t1, exit_loop 	# exit if loop counter($s1) = 16
    		lw $t4, 0($t0)          	# load the current array element into $t4 from M($t0)
   			add $t3, $t3, $t4       	# add the current element to the sum($t3)
   				# Debugging - Prints each step of addition1
				li $v0, 1
				la $a0, ($t3)
				syscall
				li $v0, 4
				la $a0, arrow
				syscall	
   			addi $t0, $t0, 4        	# increment the array address by 4 bytes (since it's a word array)
   			addi $s1, $s1, 1        	# increment the loop counter i
			j calculate_sum				# jump back to the start of the loop

# ======================================================================= #

get_parity:
	addiu $sp, $sp, -4  	# allocate space on the stack
	sw $ra, 0($sp)      	# store the return address to the stack
	li $v0, 4				# [Service]: Print String
	la $a0, parity			# "Party: "
	syscall
    lw $t0, sum            	# load the sum into $t0
	li $v0, 1              	# [Service]: Print Integer
    andi $t1, $t0, 1       	# bitwise AND between the sum and 1 to check the least significant bit
    # Even:
    beq $t1, $zero, even   	# if the result is 0, jump to even
    # Odd:
    li $a0, 1              	# load 1 (for odd) into $a0
	j print_result

	even:
    	li $a0, 0              	# load 0 (for even) into $a0

	print_result:
    	syscall                	# print the result (0 or 1)
    	jal new_line
    	jal new_line
    	lw $ra, 0($sp)      	# load the return address from the stack
		addiu $sp, $sp, 4   	# deallocate the stack space
    	jr $ra                 	# return

# ======================================================================= #

print_M:
	addiu $sp, $sp, -4  		# allocate space on the stack
	sw $ra, 0($sp)      		# store the return address to the stack
	li $s1, 0              		# initialize row counter i to 0 ($s1)
    li $s2, 0            		# initialize column counter j to 0 ($s2)
    li $t0, 4             		# store constant '4' for loop termination
    li $v0, 4					# [Service]: Print String
 	# if (matrix counter($s0) == 1 or 2 or 3) { branch
	li $t4, 1
	beq $s0, $t4, print_M1		# branch if $s0(matrix counter) == 1
	li $t4, 2
	beq $s0, $t4, print_M2		# branch if $s0(matrix counter) == 2
	li $t4, 3
	beq $s0, $t4, print_MM		# branch if $s0(matrix counter) == 3
	# } else {
	lw $ra, 0($sp)      		# load the return address from the stack
	addiu $sp, $sp, 4   		# deallocate the stack space
	jr $ra
	
	print_M1:
    	la $a0, M1_label      		# load address of M1_label
    	syscall
    	jal new_line
		la $t5, M1					# load address of integer matrix M1 into $t5
    	jal print_M_loop
    	jal new_line
    	lw $ra, 0($sp)      		# load the return address from the stack
		addiu $sp, $sp, 4   		# deallocate the stack space
		jr $ra 
	
	print_M2:
    	la $a0, M2_label      		# load address of M2_label
    	syscall
    	jal new_line
		la $t5, M2					# load address of integer matrix M2 into $t5
    	jal print_M_loop
    	jal new_line
    	lw $ra, 0($sp)      		# load the return address from the stack
		addiu $sp, $sp, 4   		# deallocate the stack space
		jr $ra 

	print_MM:
		la $a0, MM_label      		# load address of M2_label
    	syscall
    	jal new_line
		la $t5, MM					# load address of integer matrix M2 into $t5
    	jal print_M_loop
    	jal new_line
    	lw $ra, 0($sp)      		# load the return address from the stack
		addiu $sp, $sp, 4   		# deallocate the stack space
		jr $ra 

		print_M_loop:
    		beq $s1, $t0, next_row		# if i == 4, go to next row
    		beq $s2, $t0, exit_loop		# if j == 4, exit
			# Create offset
	   		la $t2, ($t5)				# load address of integer matrix M into $t2 from $t5
	   		sll $t4, $s1, 2				# calculate column offset, $t4 = $s1 * 4, 2^2 = 4
	    	add $t2, $t2, $t4           # add column number to offset to get next byte
    		sll $t4, $s2, 4             # calculate row offset, $t4 = $s1 * 16, 16 = 2^4
    		add $t2, $t2, $t4           # add row offset (in $t4) to the address of M1
    		lw $a0, 0($t2)              # load integer from M1($s2) into $t3
			# Print integer
    		li $v0, 1                   # [Service]: Print Integer in $a0
    		syscall
			li $v0, 11					# [Service]: Print Character
			li $a0, 32					# load ASCII value for space into $a0
			syscall
			# Loop
    		addi $s1, $s1, 1            # increment column counter i
    		j print_M_loop             	# jump to print_M1_loop

			next_row:
    			addiu $sp, $sp, -4  		# allocate space on the stack
				sw $ra, 0($sp)      		# store the return address to the stack
    			li $s1, 0                   # reset column counter j to 0 ($s2)
    			addi $s2, $s2, 1            # increment row counter j
    			jal new_line                # call new_line to print a new line
    			lw $ra, 0($sp)      		# load the return address from the stack
				addiu $sp, $sp, 4   		# deallocate the stack space
    			j print_M_loop             	# jump to print_M1_loop   

# ======================================================================= #

multiply_matrix:
	addiu $sp, $sp, -4  	# allocate space on the stack
	sw $ra, 0($sp)      	# store the return address to the stack
	li $v0, 4
	la $a0, MM_process
	syscall
	jal new_line
	la $s5, M1 				# load the base address of matrix M1 into $s5
	la $s6, M2 				# load the base address of matrix M2 into $s6
	la $s7, MM 				# load the base address of matrix MM into $s7

	# Save Loop termination value (4) + Initialize loop variables
	li $t1, 4 				# $t1 = 4 (row size/loop end)
	li $s1, 0 				# i = 0; initialize 1st for loop
	
	L1: li $s2, 0 				# j = 0; restart 2nd for loop
	L2: li $s3, 0 				# k = 0; restart 3rd for loop

		# First Step: skip over the i “1D Arrays” / Rows, to get the one we want.
		# So: multiply the index in the first dimension by the size of the row, 4.
		# Since 4 is a power of 2, we can use a shift instead:
		sll $t2, $s1, 2 		# $t2 = i * 2^2 (size of row of c)

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
		L3: sll $t0, $s3, 2 		# $t0 = k * 2^2 (size of row of b)
			addu $t0, $t0, $s2		# $t0 = k * size(row) + j
			sll $t0, $t0, 2 		# $t0 = byte offset of [k][j]
			addu $t0, $s6, $t0 		# $t0 = byte address of b[k][j]
			lw $t4, 0($t0) 			# $t4 = 4 bytes of b[k][j]

			# Similarly, the next five instructions are like the last five:
			# Calculate the address and then load the number a[i][k].
			sll $t0, $s1, 2 		# $t0 = i * 4 (size of row of a)
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
			bne $s3, $t1, L3 		# if (k != 32) go to L3
			sw $t3, 0($t2) 			# c[i][j] = $t3

			# Similarly, these final four instructions increment the index variable of the
			# middle and outermost loops, looping back if the index is not 4 and exiting if
			# the index is 4.
			addiu $s2, $s2, 1 		# $j = j + 1
					# Debugging - Prints everytime j loop
					li $v0, 4
					la $a0, loop_j_fin
					syscall 
			bne $s2, $t1, L2 		# if (j != 4) go to L2
			addiu $s1, $s1, 1 		# $i = i + 1
					# Debugging - Prints everytime i loop
					li $v0, 4
					la $a0, loop_i_fin
					syscall
					jal new_line 
			bne $s1, $t1, L1 		# if (i != 4) go to L1
			
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
# Input 4x4 Matrix 1
# Row 1: 1 2 3 4 -> 1 2 3 4 
# Row 2: 1 2 3 4 -> 1 2 3 4 
# Row 3: 1 2 3 4 -> 1 2 3 4 
# Row 4: 1 2 3 4 -> 1 2 3 4 
# Sum: 1 -> 3 -> 6 -> 10 -> 11 -> 13 -> 16 -> 20 -> 21 -> 23 -> 26 -> 30 -> 31 -> 33 -> 36 -> 40 -> 40
# Parity: 0
# 
# Printing M1 Matrix...
# 1 2 3 4 
# 1 2 3 4 
# 1 2 3 4 
# 1 2 3 4 
# 
# Input 4x4 Matrix 2
# Row 1: 1 2 3 4 -> 1 2 3 4 
# Row 2: 1 2 3 4 -> 1 2 3 4 
# Row 3: 1 2 3 4 -> 1 2 3 4 
# Row 4: 1 2 3 4 -> 1 2 3 4 
# Sum: 1 -> 3 -> 6 -> 10 -> 11 -> 13 -> 16 -> 20 -> 21 -> 23 -> 26 -> 30 -> 31 -> 33 -> 36 -> 40 -> 40
# Parity: 0
# 
# Printing M2 Matrix...
# 1 2 3 4 
# 1 2 3 4 
# 1 2 3 4 
# 1 2 3 4 
# 
# Multiplying M1 to M2...
# Loop j finished Loop j finished Loop j finished Loop j finished Loop i finished 
# Loop j finished Loop j finished Loop j finished Loop j finished Loop i finished 
# Loop j finished Loop j finished Loop j finished Loop j finished Loop i finished 
# Loop j finished Loop j finished Loop j finished Loop j finished Loop i finished 
# 
# Printing MM Matrix...
# 10 20 30 40 
# 10 20 30 40 
# 10 20 30 40 
# 10 20 30 40


# ======================={ Output Two }======================= #
# Input 4x4 Matrix 1
# Row 1: 1231231 -> 1 3 2 1 
# Row 2: 2312312 -> 2 1 3 2 
# Row 3: 3123123 -> 3 2 1 3 
# Row 4: 1212312 -> 1 1 3 2 
# Sum: 1 -> 4 -> 6 -> 7 -> 9 -> 10 -> 13 -> 15 -> 18 -> 20 -> 21 -> 24 -> 25 -> 26 -> 29 -> 31 -> 31
# Parity: 1
# 
# Printing M1 Matrix...
# 1 3 2 1 
# 2 1 3 2 
# 3 2 1 3 
# 1 1 3 2 
# 
# Input 4x4 Matrix 2
# Row 1: 5555555 -> 5 5 5 5 
# Row 2: 5555555 -> 5 5 5 5 
# Row 3:         -> 0 0 0 0 
# Row 4: 0000000 -> 0 0 0 0 
# Sum: 5 -> 10 -> 15 -> 20 -> 25 -> 30 -> 35 -> 40 -> 40 -> 40 -> 40 -> 40 -> 40 -> 40 -> 40 -> 40 -> 40
# Parity: 0
# 
# Printing M2 Matrix...
# 5 5 5 5 
# 5 5 5 5 
# 0 0 0 0 
# 0 0 0 0 
# 
# Multiplying M1 to M2...
# Loop j finished Loop j finished Loop j finished Loop j finished Loop i finished 
# Loop j finished Loop j finished Loop j finished Loop j finished Loop i finished 
# Loop j finished Loop j finished Loop j finished Loop j finished Loop i finished 
# Loop j finished Loop j finished Loop j finished Loop j finished Loop i finished 
# 
# Printing MM Matrix...
# 20 20 20 20 
# 15 15 15 15 
# 25 25 25 25 
# 10 10 10 10 
