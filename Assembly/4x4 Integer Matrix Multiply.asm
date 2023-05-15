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
				.align 4		# ensures V is aligned to a word boundary
M1:				.space 64		# 4x4 Matrix with 64 bytes of memory
				.align 4		# ensures V is aligned to a word boundary
M2:				.space 64		# 4x4 Matrix with 64 bytes of memory
# Variables for Debugging
arrow:			.asciiz			" -> "
print_sum:		.asciiz			"Sum: "
				.align 4		# ensures V is aligned to a word boundary
sum:			.space 4		# Sum of Matrix
parity:			.asciiz			"Parity: "
inside_M1: 		.asciiz			"(Inside make_matrix 1) "
inside_M2: 		.asciiz			"(Inside make_matrix 2) "
MTest:			.asciiz			"1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4"
 
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
    move $s0, $ra           	# save the return address in $s0
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
    sll $t4, $s3, 4				# calculate row offset, $s4 = $s3 * 16, 16 = 2^4
    li $t0, 8              		# store constant '8' for loop termination
    jal string_to_integer		# Convert Row into integer + Stored in Matrix
    jal reset_i_j				# Resets index of string and integer
    # Store updated array index
    addi $s3, $s3, 1			# loop index k + 1
    move $ra, $s0				# restore the return address from $s0
    j make_matrix_M				# Loop: jump to string_to_integer

	string_to_integer:
   		move $s7, $ra				# save the return address in $s7
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
		jal print_space
		# Store integer in 
		li $t3, 1
		beq $t6, $t3, make_M1		# branch if t6(matrix counter) == 1
		li $t3, 2
		beq $t6, $t3, make_M2		# branch if t6(matrix counter) == 2

		make_M1:
			la $s4, M1                	# load address of integer matrix M1 into $s4
			add $s4, $s4, $t4        	# add row offset (in $s4) to the address of M1
			add $s4, $s4, $s2        	# add column number to offset to get next byte
			sw $t2, 0($s4)           	# store new integer($a0) into M1($t3)
			# Store updated array index
			addi $s1, $s1, 2       		# i (input index) + 2 (2 digits + ' ')
			addi $s2, $s2, 4			# j (int array) + 4 (next byte)
			move $ra, $s7				# restore the return address from $s0
			j string_to_integer			# Loop: jump to string_to_integer

		make_M2:
			la $s5, M2                	# load address of integer matrix M2 into $s5
			add $s5, $s5, $t4        	# add row offset (in $s5) to the address of M2
			add $s5, $s5, $s2        	# add column number to offset to get next byte
			sw $t2, 0($s5)           	# store new integer($a0) into M2($s5)
			# Store updated array index
			addi $s1, $s1, 2       		# i (input index) + 2 (2 digits + ' ')
			addi $s2, $s2, 4			# j (int array) + 4 (next byte)
			move $ra, $s7				# restore the return address from $s0
			j string_to_integer			# Loop: jump to string_to_integer

	get_row:
   	 	add $t6, $t6, 1				# to print next matrix "0 -> 1 -> 2"
		add $t7, $t7, 1				# stops "Input Matrix" from looping by $t7 + 1
		li $s2, 0					# resets column counter (j / $s2) to 0
		li $v0, 4					# [Service]: Print String
		la $a0, ask_matrix			# load address of ask_matrix
		syscall
		li $v0, 1					# [Service]: Print Integer
		la $a0, ($t6)				# load address of matrix number
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
		move $ra, $s0          		# restore the return address from $s0
		jr $ra						# Return from subroutine (using $ra)

# ======================================================================= #

# $t0 = M
# $t1 = termination value
# $t2 = 
# $t3 = temp storage for sum
# $t4 = branch condition
# $t5 = 
# $t6 = matrix number (0 -> 1 -> 2 -> ...)
# $t7 = branch condition checker
get_sum:
	move $s0, $ra				# save the return address in $s0
	li $v0, 4					# [Service]: Print String
	la $a0, print_sum			# load "Sum: "
	syscall
	li $s1, 0					# reset i to 0 ($s1)
	li $t3, 0          			# initialize sum to 0
	li $t1, 16					# termination value = size of matrix, 4x4=16
	# if (matrix counter($t6) == 1 or 2) { 
	li $t4, 1
	beq $t6, $t4, load_M1		# branch if t6(matrix counter) == 1
	li $t4, 2
	beq $t6, $t4, load_M2		# branch if t6(matrix counter) == 2
	# } else {
	move $ra, $s0				# restore the return address from $s0
	jr $ra

	load_M1:
		la $t0, M1          		# load the address of the array into $t0
				# Debugging - Print "Inside M1"
				# li $v0, 4
				# la $a0, inside_M1
				# syscall
		jal calculate_sum			# calculate sum into $t3
		sw $t3, sum 	     		# store the sum
		
		li $v0, 1              		# [Service]: Print Integer
		lw $a0, sum        	  		# load sum
		syscall
		jal new_line
		move $ra, $s0				# restore the return address from $s0
		jr $ra
	
	load_M2:
		la $t0, M2          		# load the address of the array into $t0
				# Debugging - Print "Inside M1"
				# li $v0, 4
				# la $a0, inside_M2
				# syscall	
		jal calculate_sum			# calculate sum into $t3
		sw $t3, sum 	     		# store the sum
		
		li $v0, 1              		# [Service]: Print Integer
		move $a0, $t3        	  	# move the sum to $a0
		syscall
		jal new_line
		move $ra, $s0				# restore the return address from $s0
		jr $ra
		
		calculate_sum:
    		beq $s1, $t1, exit_loop 	# exit if loop counter($s1) = 16
    		lw $t4, 0($t0)          	# load the current array element into $t4 from M($t0)
   			add $t3, $t3, $t4       	# add the current element to the sum($t3)
   				# Debugging - Prints each step of addition1 2 3 4
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
	li $v0, 4					# [Service]: Print String
	la $a0, parity				# "Parity: "
	syscall
	move $s0, $ra				# save the return address in $s0
    lw $t0, sum					# load the sum into $t0
	li $v0, 1					# [Service]: Print Integer
    andi $t1, $t0, 1			# bitwise AND between the sum and 1 to check the least significant bit
    beq $t1, $zero, even		# if the result is 0, jump to even
    li $a0, 1					# load 1 (for odd) into $a0
    jal print_result			# jump to print_result
   	move $ra, $s0				# move return address back to $ra
   	jr $ra

	even:
   		li $a0, 0					# load 0 (for even) into $a0
   		j print_result

	print_result:
    	syscall						# print the result (0 or 1)
    	jal new_line
    	jal new_line
   	 	move $ra, $s0				# restore the return address from $s0
    	jr $ra						# return

# ======================================================================= #

print_M:
	move $s0, $ra				# save the return address in $s0
    li $s1, 0              		# initialize row counter i to 0 ($s1)
    li $s2, 0            		# initialize column counter j to 0 ($s2)
    li $t0, 4             		# store constant '4' for loop termination
    li $v0, 4					# [Service]: Print String
 	# if (matrix counter($t6) == 1 or 2 or 3) { branch
	li $t4, 1
	beq $t6, $t4, print_M1		# branch if t6(matrix counter) == 1
	li $t4, 2
	beq $t6, $t4, print_M2		# branch if t6(matrix counter) == 2
	li $t4, 3
	beq $t6, $t4, print_MM		# branch if t6(matrix counter) == 3
	# } else {
	move $ra, $s0				# restore the return address from $s0
	jr $ra
	
	print_M1:
    	la $a0, M1_label      		# load address of M1_label
    	syscall
    	jal new_line
		la $t5, M1					# load address of integer matrix M1 into $t5
    	jal print_M_loop
    	jal new_line
    	move $ra, $s0               # restore the return address from $s0
		jr $ra 
	
	print_M2:
    	la $a0, M2_label      		# load address of M2_label
    	syscall
    	jal new_line
		la $t5, M2					# load address of integer matrix M2 into $t5
    	jal print_M_loop
    	jal new_line
    	move $ra, $s0               # restore the return address from $s0
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
    			move $s7, $ra				# save the return address in $s7
    			li $s1, 0                   # reset column counter j to 0 ($s2)
    			addi $s2, $s2, 1            # increment row counter j
    			jal new_line                # call new_line to print a new line
    			move $ra, $s7				# return address from $s7
    			j print_M_loop             	# jump to print_M1_loop   

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

